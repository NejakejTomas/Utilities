package cz.nejakejtomas.kmp.utils.compose

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Suppress("unused", "UnusedReceiverParameter")
@Composable
fun ColumnScope.Space(size: Dp) {
    Spacer(Modifier.height(size))
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
    minSize: Dp,
    @FloatRange(from = 0.0, fromInclusive = false) weight: Float,
    fill: Boolean = true,
) {
    Spacer(Modifier.sizeIn(minHeight = minSize).weight(weight = weight, fill = fill))
}

@Suppress("unused", "UnusedReceiverParameter")
@Composable
fun RowScope.Space(size: Dp) {
    Spacer(Modifier.width(size))
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
    minSize: Dp,
    @FloatRange(from = 0.0, fromInclusive = false) weight: Float,
    fill: Boolean = true,
) {
    Spacer(Modifier.sizeIn(minWidth = minSize).weight(weight = weight, fill = fill))
}