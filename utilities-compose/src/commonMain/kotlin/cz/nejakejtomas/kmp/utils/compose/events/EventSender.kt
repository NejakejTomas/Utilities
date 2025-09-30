package cz.nejakejtomas.kmp.utils.compose.events

interface EventSender<T> {
    suspend fun send(event: T)
}