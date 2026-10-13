package cz.nejakejtomas.kmp.utils.core.events

import cz.nejakejtomas.kmp.utils.core.runSuspendCatching
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
    private class Envelope<T>(val event: T, val origin: Throwable)

    private val channel = Channel<Envelope<T>>(capacity = Channel.UNLIMITED)
    private val flow: Flow<Envelope<T>>
        get() = channel.receiveAsFlow()

    override suspend fun observe(observer: EventObserver<T>): Nothing {
        withContext(Dispatchers.Main.immediate) {
            flow.collect { envelope ->
                runSuspendCatching {
                    observer.onEvent(envelope.event)
                }.onFailure {
                    it.attachOrigin(envelope.origin)
                    throw it
                }
            }
        }

        throw IllegalStateException()
    }

    override suspend fun send(event: T) = send(
        event = event,
        origin = platformOrigin(),
    )

    internal suspend fun send(event: T, origin: Throwable) = withContext(Dispatchers.Main) {
        channel.send(
            Envelope(
                event = event,
                origin = origin,
            ),
        )
    }
}

@Suppress("unused")
context(scope: CoroutineScope)
fun <T> UiEventQueue<T>.post(event: T) {
    // DO NOT INLINE - needs to have call stack before launch
    val origin = platformOrigin()

    scope.launch {
        send(
            event = event,
            origin = origin,
        )
    }
}