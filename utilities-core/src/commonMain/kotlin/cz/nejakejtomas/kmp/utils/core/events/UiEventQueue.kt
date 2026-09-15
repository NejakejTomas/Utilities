package cz.nejakejtomas.kmp.utils.core.events

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Deprecated(
    message = "Renamed",
    replaceWith = ReplaceWith(
        expression = "UiEventQueue<T>",
        imports = ["cz.nejakejtomas.kmp.utils.core.events.UiEventQueue"],
    ),
)
typealias EventQueue<T> = UiEventQueue<T>

class UiEventQueue<T> : EventEmitter<T>, EventSender<T> {
    private val channel = Channel<T>(capacity = Channel.UNLIMITED)
    private val flow: Flow<T>
        get() = channel.receiveAsFlow()

    override suspend fun observe(observer: EventObserver<T>): Nothing {
        withContext(Dispatchers.Main.immediate) {
            flow.collect(observer::onEvent)
        }

        throw IllegalStateException()
    }

    override suspend fun send(event: T) = withContext(Dispatchers.Main) {
        channel.send(event)
    }
}

@Suppress("unused")
context(scope: CoroutineScope)
fun <T> UiEventQueue<T>.post(event: T) {
    scope.launch {
        send(event)
    }
}