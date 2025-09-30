package cz.nejakejtomas.kmp.utils.compose.snackbar

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ComposeSnackbar private constructor(
    private val snackbarHostState: SnackbarHostState,
    private val coroutineScope: CoroutineScope,
) : Snackbar {
    override fun show(message: String) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(message)
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