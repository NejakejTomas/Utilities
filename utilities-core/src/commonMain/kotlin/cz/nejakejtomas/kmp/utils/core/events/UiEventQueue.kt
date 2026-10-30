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
public typealias EventQueue<T> = UiEventQueue<T>

public class UiEventQueue<T> : EventEmitter<T>, EventSender<T> {
    private class Envelope<T>(val event: T, val origin: Throwable?)

    private val channel = Channel<Envelope<T>>(capacity = Channel.UNLIMITED)
    private val flow: Flow<Envelope<T>>
        get() = channel.receiveAsFlow()

    override suspend fun observe(observer: EventObserver<T>): Nothing {
        withContext(Dispatchers.Main.immediate) {
            flow.collect { envelope ->
                runSuspendCatching {
                    observer.onEvent(envelope.event)
                }.onFailure {
                    envelope.origin?.let { origin ->
                        it.attachOrigin(origin)
                    }

                    throw it
                }
            }
        }

        throw IllegalStateException()
    }

    public override suspend fun send(event: T): Unit = send(
        event = event,
        origin = platformOrigin(),
    )

    @DestroysStackTrace
    public suspend fun sendFast(event: T): Unit = send(
        event = event,
        origin = null,
    )

    internal suspend fun send(event: T, origin: Throwable?) = withContext(Dispatchers.Main) {
        channel.send(
            Envelope(
                event = event,
                origin = origin,
            ),
        )
    }
}

context(scope: CoroutineScope)
public fun <T> UiEventQueue<T>.post(event: T) {
    // DO NOT INLINE - needs to have call stack before launch
    val origin = platformOrigin()

    scope.launch {
        send(
            event = event,
            origin = origin,
        )
    }
}

@DestroysStackTrace
context(scope: CoroutineScope)
public fun <T> UiEventQueue<T>.postFast(event: T) {
    scope.launch {
        sendFast(event)
    }
}