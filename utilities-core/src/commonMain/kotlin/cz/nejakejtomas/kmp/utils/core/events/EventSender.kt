package cz.nejakejtomas.kmp.utils.core.events

interface EventSender<T> {
    suspend fun send(event: T)
}