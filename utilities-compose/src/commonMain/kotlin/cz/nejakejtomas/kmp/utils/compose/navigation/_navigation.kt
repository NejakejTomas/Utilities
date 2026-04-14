package cz.nejakejtomas.kmp.utils.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.runtime.serialization.NavBackStackSerializer
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.KSerializer

@IgnorableReturnValue
@Suppress("unused")
fun <T> MutableList<T>.removeLastIf(value: T): Boolean {
    if (lastOrNull() != value) return false

    val last = removeLastOrNull()

    if (last != value) {
        last?.let(::add)
        return false
    }

    return true
}

@IgnorableReturnValue
@Suppress("unused")
context(value: T)
fun <T> MutableList<T>.removeLastIfCurrent(): Boolean {
    if (lastOrNull() != value) return false

    val last = removeLastOrNull()

    if (last != value) {
        last?.let(::add)
        return false
    }

    return true
}

@Composable
@PublishedApi
internal fun <T : NavKey> rememberNavBackStack(
    elementSerializer: KSerializer<T>,
    vararg elements: T,
): NavBackStack<T> {
    return rememberSerializable(
        serializer = NavBackStackSerializer(elementSerializer = elementSerializer)
    ) {
        NavBackStack(*elements)
    }
}

@Composable
@Suppress("unused")
inline fun <Route : NavKey, Screen : Route, Dialog : Route> AppNavHost(
    startScreen: Screen,
    noinline onExit: () -> Unit,
    modifier: Modifier = Modifier,
    elementSerializer: KSerializer<Route>,
    screens: AppEntryProviderScope<Route, Screen, Dialog>.() -> Unit,
) = AppNavHost(
    onExit = onExit,
    modifier = modifier,
    backStack = rememberNavBackStack(
        elementSerializer = elementSerializer,
        startScreen,
    ),
    screens = screens,
)

@Composable
@Suppress("unused")
inline fun <Route : NavKey, Screen : Route, Dialog : Route> AppNavHost(
    noinline onExit: () -> Unit,
    modifier: Modifier = Modifier,
    backStack: NavBackStack<Route>,
    screens: AppEntryProviderScope<Route, Screen, Dialog>.() -> Unit,
) {
    val dialogStrategy = remember { DialogSceneStrategy<Route>() }

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        sceneStrategy = dialogStrategy,
        entryProvider = entryProvider(builder = {
            screens(AppEntryProviderScope(this, backStack))
        }),
        onBack = {
            if (backStack.size <= 1) onExit()
            else backStack.removeLastOrNull()
        }
    )
}