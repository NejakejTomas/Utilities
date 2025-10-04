package cz.nejakejtomas.kmp.utils.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.nejakejtomas.kmp.utils.core.flow.combine
import cz.nejakejtomas.kmp.utils.core.serialization.Savable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.coroutines.CoroutineContext

abstract class SavableViewModel<SavedState : Savable, State : Any, UiState : Any>(
    savedStateHandle: SavedStateHandle,
    initialSavedState: SavedState,
) : ViewModel(), CoroutineScope {
    protected abstract val state: StateFlow<State>
    protected abstract fun toUiState(savedState: SavedState, state: State): UiState

    private val savedState = savedStateHandle.getMutableStateFlow(
        key = SAVED_STATE_KEY,
        initialValue = initialSavedState
    )

    // This has to be kept lazy because `state` is abstract and therefore won't be initialized yet
    @Suppress("unused")
    val uiState: StateFlow<UiState> by lazy { combine(savedState, state, ::toUiState) }

    @Suppress("unused")
    protected fun updateSavedState(block: (SavedState) -> SavedState) {
        savedState.update(block)
    }

    final override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext

    companion object {
        private val SAVED_STATE_KEY =
            "${SavableViewModel::class.qualifiedName ?: throw IllegalStateException()}.SAVED_STATE"
    }
}