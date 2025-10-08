package cz.nejakejtomas.kmp.utils.viewmodel.feature

import androidx.lifecycle.SavedStateHandle
import cz.nejakejtomas.kmp.utils.core.serialization.Savable
import kotlinx.coroutines.flow.MutableStateFlow

interface SavedStateFeature<SavedState : Savable> {
    val savedState: MutableStateFlow<SavedState>

    companion object {
        private val SAVED_STATE_KEY =
            "${SavedStateFeature::class.qualifiedName ?: throw IllegalStateException()}.SAVED_STATE"

        operator fun <SavedState : Savable> invoke(
            savedStateHandle: SavedStateHandle,
            initialValue: SavedState
        ): SavedStateFeature<SavedState> {
            return object : SavedStateFeature<SavedState> {
                override val savedState: MutableStateFlow<SavedState> =
                    savedStateHandle.getMutableStateFlow(SAVED_STATE_KEY, initialValue)
            }
        }
    }
}