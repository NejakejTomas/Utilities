package cz.nejakejtomas.kmp.utils.compose.snackbar

import androidx.compose.runtime.compositionLocalOf

val LocalSnackbar = compositionLocalOf<Snackbar> { NoopSnackbar }