package cz.nejakejtomas.kmp.utils.core.events

fun interface EventObserver<in T> {
    suspend fun onEvent(value: T)
}