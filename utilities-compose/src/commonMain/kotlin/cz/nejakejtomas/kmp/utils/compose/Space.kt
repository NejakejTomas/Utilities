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

@Suppress("UnusedReceiverParameter")
@Composable
public fun ColumnScope.Space(height: Dp) {
    Spacer(Modifier.height(height))
}

@Composable
public fun ColumnScope.Space(
    @FloatRange(from = 0.0, fromInclusive = false) weight: Float,
    fill: Boolean = true,
) {
    Spacer(Modifier.weight(weight = weight, fill = fill))
}

@Composable
public fun ColumnScope.Space(
    minHeight: Dp,
    @FloatRange(from = 0.0, fromInclusive = false) weight: Float,
    fill: Boolean = true,
) {
    Spacer(Modifier.height(minHeight))
    Spacer(Modifier.weight(weight = weight, fill = fill))
}

@Suppress("UnusedReceiverParameter")
@Composable
public fun RowScope.Space(width: Dp) {
    Spacer(Modifier.width(width))
}

@Composable
public fun RowScope.Space(
    @FloatRange(from = 0.0, fromInclusive = false) weight: Float,
    fill: Boolean = true,
) {
    Spacer(Modifier.weight(weight = weight, fill = fill))
}

@Composable
public fun RowScope.Space(
    minWidth: Dp,
    @FloatRange(from = 0.0, fromInclusive = false) weight: Float,
    fill: Boolean = true,
) {
    Spacer(Modifier.width(minWidth))
    Spacer(Modifier.weight(weight = weight, fill = fill))
}