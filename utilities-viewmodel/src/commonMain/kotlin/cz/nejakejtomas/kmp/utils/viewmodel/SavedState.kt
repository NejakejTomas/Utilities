package cz.nejakejtomas.kmp.utils.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.serialization.saved
import androidx.savedstate.serialization.SavedStateConfiguration
import cz.nejakejtomas.kmp.utils.core.serialization.Savable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.properties.ReadWriteProperty

private class SavedStateKey

@PublishedApi
internal val SAVED_STATE_KEY: String = SavedStateKey::class.qualifiedName!!

@Suppress("unused")
object SavedState {
    @Deprecated(message = "Deprecated, replace with delegated invoke")
    operator fun <SavedState : Savable> invoke(
        savedStateHandle: SavedStateHandle,
        initialValue: SavedState
    ): MutableStateFlow<SavedState> =
        savedStateHandle.getMutableStateFlow(SAVED_STATE_KEY, initialValue)

    inline operator fun <reified SavedState> invoke(
        savedStateHandle: SavedStateHandle,
        configuration: SavedStateConfiguration = SavedStateConfiguration.DEFAULT,
        noinline initialValue: () -> SavedState
    ): ReadWriteProperty<Any?, MutableStateFlow<SavedState>> = savedStateHandle.saved(
        key = SAVED_STATE_KEY,
        configuration = configuration,
    ) {
        MutableStateFlow(initialValue())
    }
}