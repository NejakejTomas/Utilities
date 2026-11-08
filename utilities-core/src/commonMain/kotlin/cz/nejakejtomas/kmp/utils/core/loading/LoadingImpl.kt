package cz.nejakejtomas.kmp.utils.core.loading

import cz.nejakejtomas.kmp.utils.core.flow.combine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

internal class LoadingImpl : Loading {
    private val _state = MutableStateFlow(0)

    context(scope: CoroutineScope)
    override val state: StateFlow<Boolean>
        get() = scope.combine(_state) { it == 0 }

    override operator fun <T> invoke(block: () -> T): T {
        _state.update { it + 1 }
        return try {
            block()
        } finally {
            _state.update { it - 1 }
        }
    }
}