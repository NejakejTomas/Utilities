package cz.nejakejtomas.kmp.utils.compose.prompts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.job
import kotlin.coroutines.CoroutineContext
import kotlin.uuid.Uuid

typealias CreatePrompt<T, Context> = @Composable (
    canSurviveProcessDeath: Boolean,
    context: Context,
    onSubmit: (T) -> Unit,
) -> Unit

@Suppress("unused")
abstract class PromptController<T, Context> {
    internal data class RequestContext<T, Context>(
        val coroutineContext: CoroutineContext,
        val deferred: CompletableDeferred<T>,
        val canSurviveProcessDeath: Boolean,
        val context: Context
    )

    private val _activePrompts = MutableStateFlow<Map<Uuid, RequestContext<T, Context>>>(emptyMap())
    internal val activePrompts = _activePrompts.asStateFlow()

    @Suppress("unused")
    protected suspend fun request(context: Context, id: Uuid?): T {
        val canSurviveProcessDeath = id != null
        val id = id ?: Uuid.random()
        val deferred = CompletableDeferred<T>()
        val requestContext =
            RequestContext(currentCoroutineContext(), deferred, canSurviveProcessDeath, context)

        _activePrompts.update { it + (id to requestContext) }

        try {
            return deferred.await()
        } finally {
            _activePrompts.update { it - id }
        }
    }

    internal fun submit(id: Uuid, value: T) {
        _activePrompts.value[id]?.deferred?.complete(value)
    }

    internal fun cancel(id: Uuid) {
        _activePrompts.value[id]?.deferred?.completeExceptionally(CancellationException())
    }
}

@Composable
@Suppress("unused")
fun <T, Context> PromptController<T, Context>.PromptHost(createPrompt: CreatePrompt<T, Context>) {
    val activePrompts by activePrompts.collectAsStateWithLifecycle()

    for ((id, request) in activePrompts) {
        key(id) {
            val onCancel = { cancel(id) }

            // Close when context is closed
            LaunchedEffect(request.context) {
                request.coroutineContext.job.invokeOnCompletion {
                    onCancel()
                }
            }

            createPrompt(request.canSurviveProcessDeath, request.context, { submit(id, it) })
        }
    }
}