package cz.nejakejtomas.kmp.utils.compose.snackbar

import androidx.compose.material3.SnackbarDuration

@ConsistentCopyVisibility
public data class SnackbarVisuals internal constructor(
    override val message: String,
    override val actionLabel: String?,
    override val withDismissAction: Boolean,
    override val duration: SnackbarDuration,
    val colors: SnackbarColors,
) : androidx.compose.material3.SnackbarVisuals {
    public companion object {
        public operator fun invoke(
            snackbarVisuals: androidx.compose.material3.SnackbarVisuals,
            colors: SnackbarColors,
        ): SnackbarVisuals {
            return SnackbarVisuals(
                message = snackbarVisuals.message,
                actionLabel = snackbarVisuals.actionLabel,
                withDismissAction = snackbarVisuals.withDismissAction,
                duration = snackbarVisuals.duration,
                colors = colors,
            )
        }
    }
}