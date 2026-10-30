package cz.nejakejtomas.kmp.utils.core.events

public fun interface EventObserver<in T> {
    public suspend fun onEvent(value: T)
}