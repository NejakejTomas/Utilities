package cz.nejakejtomas.kmp.utils.viewmodel.feature

import cz.nejakejtomas.kmp.utils.core.events.EventEmitter

@Deprecated(
    message = "Renamed",
    replaceWith = ReplaceWith(
        expression = "UiEventsFeature<Event>",
        imports = ["cz.nejakejtomas.kmp.utils.viewmodel.feature.UiEventsFeature"],
    ),
)
typealias EventsFeature<Event> = UiEventsFeature<Event>

@Suppress("unused")
interface UiEventsFeature<Event> {
    val events: EventEmitter<Event>
}