package cz.nejakejtomas.kmp.utils.compose.snackbar

import androidx.compose.ui.graphics.Color

public object SnackbarDefaults {
    public fun snackbarColors(
        containerColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
    ): SnackbarColors = SnackbarColors(
        containerColor = containerColor,
        contentColor = contentColor,
    )
}