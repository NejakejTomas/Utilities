package cz.nejakejtomas.kmp.utils.compose

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Suppress("unused", "UnusedReceiverParameter")
@Composable
fun ColumnScope.Space(height: Dp) {
    Spacer(Modifier.height(height))
}

@Suppress("unused")
@Composable
fun ColumnScope.Space(
    @FloatRange(from = 0.0, fromInclusive = false) weight: Float,
    fill: Boolean = true,
) {
    Spacer(Modifier.weight(weight = weight, fill = fill))
}

@Suppress("unused")
@Composable
fun ColumnScope.Space(
    minHeight: Dp,
    @FloatRange(from = 0.0, fromInclusive = false) weight: Float,
    fill: Boolean = true,
) {
    Spacer(Modifier.height(minHeight))
    Spacer(Modifier.weight(weight = weight, fill = fill))
}

@Suppress("unused", "UnusedReceiverParameter")
@Composable
fun RowScope.Space(width: Dp) {
    Spacer(Modifier.width(width))
}

@Suppress("unused")
@Composable
fun RowScope.Space(
    @FloatRange(from = 0.0, fromInclusive = false) weight: Float,
    fill: Boolean = true,
) {
    Spacer(Modifier.weight(weight = weight, fill = fill))
}

@Suppress("unused")
@Composable
fun RowScope.Space(
    minWidth: Dp,
    @FloatRange(from = 0.0, fromInclusive = false) weight: Float,
    fill: Boolean = true,
) {
    Spacer(Modifier.width(minWidth))
    Spacer(Modifier.weight(weight = weight, fill = fill))
}