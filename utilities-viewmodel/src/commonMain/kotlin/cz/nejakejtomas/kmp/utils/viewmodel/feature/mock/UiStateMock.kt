package cz.nejakejtomas.kmp.utils.viewmodel.feature.mock

import cz.nejakejtomas.kmp.utils.viewmodel.feature.UiStateFeature
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Suppress("unused")
class UiStateMock<UiState>(value: UiState) : UiStateFeature<UiState> {
    private val _uiState = MutableStateFlow(value)
    override val uiState: StateFlow<UiState> = _uiState
}