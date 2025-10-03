package cz.nejakejtomas.kmp.utils.compose.events

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.withContext

@Suppress("unused")
class EventQueue<T> : EventEmitter<T>(), EventSender<T> {
    private val channel = Channel<T>(Channel.UNLIMITED)
    override val flow: Flow<T>
        get() = channel.receiveAsFlow()

    override suspend fun send(event: T) = withContext(Dispatchers.Main) {
        channel.send(event)
    }
}