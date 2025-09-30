package cz.nejakejtomas.kmp.utils.compose.snackbar

object NoopSnackbar : Snackbar {
    override fun show(message: String) {
        // No-op
    }
}