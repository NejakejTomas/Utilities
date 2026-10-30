package cz.nejakejtomas.kmp.utils.viewmodel.feature

import cz.nejakejtomas.kmp.utils.core.events.EventEmitter

@Deprecated(
    message = "Renamed",
    replaceWith = ReplaceWith(
        expression = "UiEventsFeature<Event>",
        imports = ["cz.nejakejtomas.kmp.utils.viewmodel.feature.UiEventsFeature"],
    ),
)
public typealias EventsFeature<Event> = UiEventsFeature<Event>

public interface UiEventsFeature<Event> {
    public val events: EventEmitter<Event>
}