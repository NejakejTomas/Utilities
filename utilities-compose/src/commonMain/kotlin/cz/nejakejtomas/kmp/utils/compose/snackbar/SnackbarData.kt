package cz.nejakejtomas.kmp.utils.compose.snackbar

import androidx.compose.material3.SnackbarData

class SnackbarData internal constructor(
    override val visuals: SnackbarVisuals,
    private val performActionVal: () -> Unit,
    private val dismissVal: () -> Unit,
) : SnackbarData {
    override fun performAction() = performActionVal()
    override fun dismiss() = dismissVal()
}