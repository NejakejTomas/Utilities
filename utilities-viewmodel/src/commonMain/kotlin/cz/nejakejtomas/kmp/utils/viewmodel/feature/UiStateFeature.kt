package cz.nejakejtomas.kmp.utils.viewmodel.feature

import kotlinx.coroutines.flow.StateFlow

@Suppress("unused")
interface UiStateFeature<UiState> {
    val uiState: StateFlow<UiState>
}