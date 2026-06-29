package cz.nejakejtomas.kmp.utils.compose.snackbar

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse

@Suppress("unused")
@Composable
fun SnackbarHost(
    modifier: Modifier = Modifier,
    defaultColors: SnackbarColors = SnackbarDefaults.snackbarColors(
        containerColor = androidx.compose.material3.SnackbarDefaults.color,
        contentColor = androidx.compose.material3.SnackbarDefaults.contentColor,
    ),
    snackbar: @Composable ((snackbarData: SnackbarData) -> Unit) = { snackbarData ->
        Snackbar(
            snackbarData = snackbarData,
            containerColor = snackbarData.visuals.colors.containerColor,
            contentColor = snackbarData.visuals.colors.contentColor,
            actionColor = snackbarData.visuals.colors.contentColor,
            actionContentColor = snackbarData.visuals.colors.contentColor,
            dismissActionContentColor = snackbarData.visuals.colors.contentColor,
        )
    },
    content: @Composable () -> Unit,
) {
    val snackbarHostState = LocalSnackbar.current

    @Suppress("UnusedMaterial3ScaffoldPaddingParameter")
    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState.materialSnackbarHostState,
            ) { snackbarData ->
                val snackbarColors =
                    (snackbarData.visuals as? SnackbarVisuals)?.colors ?: defaultColors

                val containerColor: Color =
                    snackbarColors.containerColor.takeOrElse { defaultColors.containerColor }
                val contentColor: Color =
                    snackbarColors.contentColor.takeOrElse { defaultColors.contentColor }

                snackbar(
                    SnackbarData(
                        visuals = SnackbarVisuals(
                            snackbarVisuals = snackbarData.visuals,
                            colors = snackbarColors.copy(
                                containerColor = containerColor,
                                contentColor = contentColor,
                            )
                        ),
                        performActionVal = snackbarData::performAction,
                        dismissVal = snackbarData::dismiss,
                    )
                )
            }
        },
        containerColor = Color.Transparent,
    ) { _ ->
        CompositionLocalProvider(
            content = content,
        )
    }
}