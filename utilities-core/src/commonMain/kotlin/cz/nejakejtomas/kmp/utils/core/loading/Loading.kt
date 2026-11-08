package cz.nejakejtomas.kmp.utils.core.loading

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

public interface Loading {
    context(_: CoroutineScope)
    public val state: StateFlow<Boolean>

    public operator fun <T> invoke(block: () -> T): T
    public fun <T : Any> ifNot(block: () -> T): T?
    public suspend fun <T> after(block: () -> T): T

    public companion object
}

public operator fun Loading.Companion.invoke(): Loading = LoadingImpl()
