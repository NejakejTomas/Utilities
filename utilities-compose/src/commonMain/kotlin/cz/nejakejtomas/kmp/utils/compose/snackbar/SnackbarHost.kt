package cz.nejakejtomas.kmp.utils.compose.snackbar

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Suppress("unused")
@Composable
fun SnackbarHost(
    modifier: Modifier = Modifier,
    snackbar: @Composable ((SnackbarData) -> Unit) = { Snackbar(it) },
    content: @Composable () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    @Suppress("UnusedMaterial3ScaffoldPaddingParameter")
    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = snackbar
            )
        }
    ) { _ ->
        CompositionLocalProvider(
            LocalSnackbar provides ComposeSnackbar(snackbarHostState),
            content = content
        )
    }
}