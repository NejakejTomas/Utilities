package cz.nejakejtomas.kmp.utils.compose.events

fun interface EventObserver<in T> {
    suspend fun emit(value: T)
}