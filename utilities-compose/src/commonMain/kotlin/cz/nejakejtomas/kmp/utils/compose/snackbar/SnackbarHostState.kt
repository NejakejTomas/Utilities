package cz.nejakejtomas.kmp.utils.compose.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult

@Suppress("unused")
class SnackbarHostState {
    internal val materialSnackbarHostState = SnackbarHostState()

    internal suspend fun show(snackbarVisuals: SnackbarVisuals): SnackbarResult {
        return materialSnackbarHostState.showSnackbar(snackbarVisuals)
    }

    suspend fun show(
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration =
            if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
        colors: SnackbarColors = SnackbarDefaults.snackbarColors()
    ): SnackbarResult {
        return show(
            SnackbarVisuals(
                message = message,
                actionLabel = actionLabel,
                withDismissAction = withDismissAction,
                duration = duration,
                colors = colors,
            )
        )
    }
}