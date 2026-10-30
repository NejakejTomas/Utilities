package cz.nejakejtomas.kmp.utils.core.events

public interface EventSender<T> {
    public suspend fun send(event: T)
}