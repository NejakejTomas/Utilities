package cz.nejakejtomas.kmp.utils.viewmodel.feature.mock

import cz.nejakejtomas.kmp.utils.core.events.EventEmitter
import cz.nejakejtomas.kmp.utils.core.events.EventObserver
import cz.nejakejtomas.kmp.utils.viewmodel.feature.EventsFeature
import kotlinx.coroutines.delay
import kotlin.time.Duration

@Suppress("unused")
data object EventsMock : EventsFeature<Nothing> {
    override val events: EventEmitter<Nothing> = object : EventEmitter<Nothing> {
        override suspend fun observe(observer: EventObserver<Nothing>): Nothing {
            delay(Duration.INFINITE)
            throw IllegalStateException()
        }
    }
}