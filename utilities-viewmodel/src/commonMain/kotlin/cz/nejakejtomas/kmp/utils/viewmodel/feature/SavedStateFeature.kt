package cz.nejakejtomas.kmp.utils.viewmodel.feature

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.StateFlow

interface SavedStateFeature<SavedState> {
    val savedState: StateFlow<SavedState>

    companion object {
        private val SAVED_STATE_KEY =
            "${SavedStateFeature::class.qualifiedName ?: throw IllegalStateException()}.SAVED_STATE"

        operator fun <SavedState> invoke(
            savedStateHandle: SavedStateHandle,
            initialValue: SavedState
        ): SavedStateFeature<SavedState> {
            return object : SavedStateFeature<SavedState> {
                override val savedState: StateFlow<SavedState> =
                    savedStateHandle.getMutableStateFlow(SAVED_STATE_KEY, initialValue)
            }
        }
    }
}