package cz.nejakejtomas.kmp.utils.compose.snackbar

import android.content.Context
import androidx.annotation.StringRes

fun Snackbar.show(context: Context, @StringRes stringRes: Int) = show(context.getString(stringRes))

fun Snackbar.show(context: Context, @StringRes stringRes: Int, vararg formatArgs: Any) =
    show(context.getString(stringRes, *formatArgs))

@Suppress("unused")
context(context: Context)
fun Snackbar.show(@StringRes stringRes: Int) = show(context, stringRes)

@Suppress("unused")
context(context: Context)
fun Snackbar.show(@StringRes stringRes: Int, vararg formatArgs: Any) =
    show(context, stringRes, *formatArgs)