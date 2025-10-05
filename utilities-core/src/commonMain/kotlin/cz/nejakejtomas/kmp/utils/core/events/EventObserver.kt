package cz.nejakejtomas.kmp.utils.core.events

fun interface EventObserver<in T> {
    suspend fun emit(value: T)
}