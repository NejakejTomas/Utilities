package cz.nejakejtomas.kmp.utils.compose.snackbar

import android.content.Context
import android.widget.Toast

@Suppress("unused")
class ToastSnackbar(private val context: Context) : Snackbar {
    override fun show(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}