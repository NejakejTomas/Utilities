package cz.nejakejtomas.kmp.utils.viewmodel.feature

import cz.nejakejtomas.kmp.utils.core.events.EventEmitter

@Suppress("unused")
interface EventsFeature<Event> {
    val events: EventEmitter<Event>
}