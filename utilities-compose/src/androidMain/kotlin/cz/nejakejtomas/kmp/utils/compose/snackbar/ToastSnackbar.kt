package cz.nejakejtomas.kmp.utils.compose.snackbar

import android.content.Context
import android.widget.Toast

@Suppress("unused")
class ToastSnackbar(private val context: Context) : Snackbar {
    override fun show(message: String, duration: Snackbar.Duration) {
        val duration = when (duration) {
            Snackbar.Duration.Short -> Toast.LENGTH_SHORT
            Snackbar.Duration.Long -> Toast.LENGTH_LONG
            Snackbar.Duration.Indefinite -> Toast.LENGTH_LONG
        }

        Toast.makeText(context, message, duration).show()
    }
}