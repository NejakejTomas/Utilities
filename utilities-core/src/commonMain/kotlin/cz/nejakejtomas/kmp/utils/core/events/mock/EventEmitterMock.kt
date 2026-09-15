package cz.nejakejtomas.kmp.utils.core.events.mock

import cz.nejakejtomas.kmp.utils.core.events.EventEmitter
import cz.nejakejtomas.kmp.utils.core.events.EventObserver
import kotlinx.coroutines.delay
import kotlin.time.Duration

@Suppress("unused")
data object EventEmitterMock : EventEmitter<Nothing> {
    override suspend fun observe(observer: EventObserver<Nothing>): Nothing {
        delay(Duration.INFINITE)
        throw IllegalStateException()
    }
}