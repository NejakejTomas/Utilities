package cz.nejakejtomas.kmp.utils.compose.snackbar

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

private val globalSnackbarHostState by lazy { SnackbarHostState() }
public val LocalSnackbar: ProvidableCompositionLocal<SnackbarHostState> =
    compositionLocalOf { globalSnackbarHostState }