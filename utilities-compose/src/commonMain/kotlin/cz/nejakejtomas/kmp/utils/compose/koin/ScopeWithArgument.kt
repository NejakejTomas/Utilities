package cz.nejakejtomas.kmp.utils.compose.koin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.retain.retain
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.rememberViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.rememberViewModelStoreProvider
import org.koin.compose.currentKoinScope
import org.koin.compose.getKoin
import org.koin.compose.scope.UnboundKoinScope
import org.koin.core.annotation.KoinDelicateAPI
import org.koin.core.annotation.KoinExperimentalAPI
import kotlin.uuid.Uuid

@Composable
public inline fun <reified T : Any> ScopeWithArgument(
    argument: T,
    noinline content: @Composable () -> Unit
) {
    val parentScope = currentKoinScope()
    val koin = getKoin()

    val scope = retain(koin, parentScope, argument) {
        val scope = koin.createScope<T>("$argument - ${Uuid.random()}")
        scope.declare<T>(argument)
        scope.linkTo(parentScope)

        RetainedKoinScope(scope)
    }.scope

    // This leaks entry in ViewModelStoreProvider, but is not elegantly solvable
    // (ViewModelStoreProvider cannot delete a single key)
    val provider = rememberViewModelStoreProvider(key = scope.id)
    val owner = rememberViewModelStoreOwner(key = scope.id, provider = provider)

    @OptIn(KoinDelicateAPI::class, KoinExperimentalAPI::class)
    UnboundKoinScope(scope) {
        CompositionLocalProvider(LocalViewModelStoreOwner provides owner) {
            content()
        }
    }
}