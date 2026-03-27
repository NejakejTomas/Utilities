package cz.nejakejtomas.kmp.utils.compose.snackbar

interface Snackbar {
    fun show(message: String, duration: Duration = Duration.Long)

    enum class Duration {
        Short,
        Long,
        Indefinite,
    }
}