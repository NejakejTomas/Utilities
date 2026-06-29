package cz.nejakejtomas.kmp.utils.compose.snackbar

import androidx.compose.ui.graphics.Color

object SnackbarDefaults {
    fun snackbarColors(
        containerColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
    ) = SnackbarColors(
        containerColor = containerColor,
        contentColor = contentColor,
    )
}