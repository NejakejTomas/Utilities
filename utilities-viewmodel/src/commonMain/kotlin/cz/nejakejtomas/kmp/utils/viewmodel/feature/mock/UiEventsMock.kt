package cz.nejakejtomas.kmp.utils.viewmodel.feature.mock

import cz.nejakejtomas.kmp.utils.core.events.EventEmitter
import cz.nejakejtomas.kmp.utils.core.events.EventObserver
import cz.nejakejtomas.kmp.utils.viewmodel.feature.UiEventsFeature
import kotlinx.coroutines.delay
import kotlin.time.Duration

@Deprecated(
    message = "Renamed",
    replaceWith = ReplaceWith(
        expression = "UiEventsMock",
        imports = ["cz.nejakejtomas.kmp.utils.viewmodel.feature.mock.UiEventsMock"],
    ),
)
typealias EventsMock = UiEventsMock

@Suppress("unused")
data object UiEventsMock : UiEventsFeature<Nothing> {
    override val events: EventEmitter<Nothing> = object : EventEmitter<Nothing> {
        override suspend fun observe(observer: EventObserver<Nothing>): Nothing {
            delay(Duration.INFINITE)
            throw IllegalStateException()
        }
    }
}