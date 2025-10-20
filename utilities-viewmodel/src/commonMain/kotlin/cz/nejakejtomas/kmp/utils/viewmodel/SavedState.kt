package cz.nejakejtomas.kmp.utils.viewmodel

import androidx.lifecycle.SavedStateHandle
import cz.nejakejtomas.kmp.utils.core.serialization.Savable
import kotlinx.coroutines.flow.MutableStateFlow

object SavedState {
    operator fun <SavedState : Savable> invoke(
        savedStateHandle: SavedStateHandle,
        initialValue: SavedState
    ): MutableStateFlow<SavedState> =
        savedStateHandle.getMutableStateFlow(SAVED_STATE_KEY, initialValue)

    private val SAVED_STATE_KEY =
        "${SavedState::class.qualifiedName ?: throw IllegalStateException()}.SAVED_STATE"
}