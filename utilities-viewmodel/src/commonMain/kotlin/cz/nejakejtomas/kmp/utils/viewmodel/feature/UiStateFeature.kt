package cz.nejakejtomas.kmp.utils.viewmodel.feature

import kotlinx.coroutines.flow.StateFlow

public interface UiStateFeature<UiState> {
    public val uiState: StateFlow<UiState>
}