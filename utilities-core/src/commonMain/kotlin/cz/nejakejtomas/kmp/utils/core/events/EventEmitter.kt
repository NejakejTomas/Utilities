package cz.nejakejtomas.kmp.utils.core.events

interface EventEmitter<out T> {
    suspend fun observe(observer: EventObserver<T>)
}