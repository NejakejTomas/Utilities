package cz.nejakejtomas.kmp.utils.viewmodel

import androidx.lifecycle.SavedStateHandle
import cz.nejakejtomas.kmp.utils.core.events.EventEmitter
import cz.nejakejtomas.kmp.utils.core.events.EventQueue
import cz.nejakejtomas.kmp.utils.core.serialization.Savable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Suppress("unused")
abstract class RichViewModel<SavedState : Savable, State : Any, UiState : Any, Event : Any>(
    savedStateHandle: SavedStateHandle,
    initialSavedState: SavedState,
) : SavableViewModel<SavedState, State, UiState>(savedStateHandle, initialSavedState) {
    private val _events = EventQueue<Event>()
    val events: EventEmitter<Event> = _events

    @Suppress("unused", "UnusedReceiverParameter")
    protected suspend fun CoroutineScope.send(event: Event) {
        _events.send(event)
    }

    protected fun send(event: Event) {
        launch {
            _events.send(event)
        }
    }
}