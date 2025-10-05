package cz.nejakejtomas.kmp.utils.viewmodel

import androidx.lifecycle.SavedStateHandle
import cz.nejakejtomas.kmp.utils.compose.events.EventEmitter
import cz.nejakejtomas.kmp.utils.compose.events.EventQueue
import cz.nejakejtomas.kmp.utils.core.serialization.Savable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Suppress("unused")
abstract class RichViewModel<SavedState : Savable, State : Any, UiState : Any, Event : Any>(
    savedStateHandle: SavedStateHandle,
    initialSavedState: SavedState,
) : SavableViewModel<SavedState, State, UiState>(savedStateHandle, initialSavedState) {
    val events: EventEmitter<Event>
        private field = EventQueue<Event>()

    @Suppress("unused", "UnusedReceiverParameter")
    protected suspend fun CoroutineScope.send(event: Event) {
        events.send(event)
    }

    protected fun send(event: Event) {
        launch {
            events.send(event)
        }
    }
}