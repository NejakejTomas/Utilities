package cz.nejakejtomas.kmp.utils.core.events

public interface EventEmitter<out T> {
    public suspend fun observe(observer: EventObserver<T>): Nothing
}