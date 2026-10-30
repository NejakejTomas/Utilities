package cz.nejakejtomas.kmp.utils.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.serialization.NavKeySerializer

@Composable
public inline fun <Route : NavKey, Screen : Route, Dialog : Route> AppNavHost(
    startScreen: Screen,
    noinline onExit: () -> Unit,
    modifier: Modifier = Modifier,
    screens: AppEntryProviderScope<Route, Screen, Dialog>.() -> Unit,
): Unit = AppNavHost(
    onExit = onExit,
    modifier = modifier,
    backStack = rememberNavBackStack(
        startScreen,
    ),
    screens = screens,
)

@Composable
public fun <T : NavKey> rememberNavBackStack(
    vararg elements: T,
): NavBackStack<T> = rememberNavBackStack(
    elementSerializer = NavKeySerializer(),
    elements = elements,
)