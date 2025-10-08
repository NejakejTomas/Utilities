package cz.nejakejtomas.kmp.utils.viewmodel.feature

import kotlinx.coroutines.flow.MutableStateFlow

@Suppress("unused")
interface MutableVolatileStateFeature<VolatileState> {
    val mutableVolatileState: MutableStateFlow<VolatileState>
}