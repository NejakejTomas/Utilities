package cz.nejakejtomas.kmp.utils.compose.snackbar

import android.content.Context
import androidx.annotation.StringRes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun SnackbarHostState.show(
    coroutineScope: CoroutineScope,
    context: Context,
    @StringRes stringRes: Int
) {
    coroutineScope.launch {
        val _ = show(context.getString(stringRes))
    }
}

fun SnackbarHostState.show(
    coroutineScope: CoroutineScope,
    context: Context,
    @StringRes stringRes: Int,
    vararg formatArgs: Any
) {
    coroutineScope.launch {
        val _ = show(context.getString(stringRes, *formatArgs))
    }
}

@Suppress("unused")
context(coroutineScope: CoroutineScope, context: Context)
fun SnackbarHostState.show(@StringRes stringRes: Int): Unit =
    show(coroutineScope, context, stringRes)

@Suppress("unused")
context(coroutineScope: CoroutineScope, context: Context)
fun SnackbarHostState.show(@StringRes stringRes: Int, vararg formatArgs: Any): Unit =
    show(coroutineScope, context, stringRes, *formatArgs)