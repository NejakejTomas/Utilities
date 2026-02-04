package cz.nejakejtomas.kmp.utils.core.events

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventQueue<T> : EventEmitter<T>, EventSender<T> {
    private val channel = Channel<T>(capacity = Channel.UNLIMITED)
    private val flow: Flow<T>
        get() = channel.receiveAsFlow()

    override suspend fun observe(observer: EventObserver<T>): Nothing {
        withContext(Dispatchers.Main.immediate) {
            flow.collect(observer::emit)
        }

        throw IllegalStateException()
    }

    override suspend fun send(event: T) = withContext(Dispatchers.Main) {
        channel.send(event)
    }
}

@Suppress("unused")
context(scope: CoroutineScope)
fun <T> EventQueue<T>.post(event: T) {
    scope.launch {
        send(event)
    }
}