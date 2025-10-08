package cz.nejakejtomas.kmp.utils.viewmodel.feature

import kotlinx.coroutines.flow.StateFlow

@Suppress("unused")
interface VolatileStateFeature<VolatileState> {
    val volatileState: StateFlow<VolatileState>
}