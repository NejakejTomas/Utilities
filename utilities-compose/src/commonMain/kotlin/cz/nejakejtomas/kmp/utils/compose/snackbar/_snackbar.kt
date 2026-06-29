package cz.nejakejtomas.kmp.utils.compose.snackbar

import androidx.compose.runtime.compositionLocalOf

private val globalSnackbarHostState by lazy { SnackbarHostState() }
val LocalSnackbar = compositionLocalOf { globalSnackbarHostState }