package cz.nejakejtomas.kmp.utils.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.serialization.NavKeySerializer

@Composable
@Suppress("unused")
inline fun <Route : NavKey, Screen : Route, Dialog : Route> AppNavHost(
    startScreen: Screen,
    noinline onExit: () -> Unit,
    modifier: Modifier = Modifier,
    screens: AppEntryProviderScope<Route, Screen, Dialog>.() -> Unit,
) = AppNavHost(
    onExit = onExit,
    modifier = modifier,
    backStack = rememberNavBackStack(
        elementSerializer = NavKeySerializer(),
        startScreen,
    ),
    screens = screens,
)