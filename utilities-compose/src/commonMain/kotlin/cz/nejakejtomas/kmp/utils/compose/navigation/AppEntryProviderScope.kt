package cz.nejakejtomas.kmp.utils.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.scene.DialogSceneStrategy

@Suppress("unused")
class AppEntryProviderScope<Route : NavKey, Screen : Route, Dialog : Route>(
    @PublishedApi internal val parent: EntryProviderScope<Route>,
    val backStack: NavBackStack<Route>,
) {
    private fun defaultContentKey(key: Any): Any = key.toString()

    inline fun <reified D : Dialog> dialog(
        noinline content: @Composable (D) -> Unit,
    ) = parent.addEntryProvider(
        clazz = D::class,
        metadata = DialogSceneStrategy.dialog(),
        content = content,
    )

    inline fun <reified S : Screen> screen(
        noinline clazzContentKey: (key: @JvmSuppressWildcards S) -> Any = { defaultContentKey(it) },
        metadata: Map<String, Any> = emptyMap(),
        noinline content: @Composable (S) -> Unit,
    ) {
        parent.addEntryProvider(
            clazz = S::class,
            clazzContentKey = clazzContentKey, metadata = metadata,
            content = content,
        )
    }
}