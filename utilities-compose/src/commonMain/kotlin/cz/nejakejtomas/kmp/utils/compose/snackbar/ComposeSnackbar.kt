package cz.nejakejtomas.kmp.utils.compose.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ComposeSnackbar private constructor(
    private val snackbarHostState: SnackbarHostState,
    private val coroutineScope: CoroutineScope,
) : Snackbar {
    override fun show(message: String, duration: Snackbar.Duration) {
        coroutineScope.launch {
            val duration = when (duration) {
                Snackbar.Duration.Short -> SnackbarDuration.Short
                Snackbar.Duration.Long -> SnackbarDuration.Long
                Snackbar.Duration.Indefinite -> SnackbarDuration.Indefinite
            }

            snackbarHostState.showSnackbar(
                message = message,
                duration = duration,
                withDismissAction = duration == SnackbarDuration.Indefinite
            )
        }
    }

    companion object {
        @Composable
        operator fun invoke(snackbarHostState: SnackbarHostState): ComposeSnackbar {
            val scope = rememberCoroutineScope()
            return ComposeSnackbar(snackbarHostState, scope)
        }
    }
}