package cz.nejakejtomas.kmp.utils.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

@Suppress("unused")
open class UtilsViewModel : ViewModel(), CoroutineScope {
    final override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext

    open val features: Any = object {}
}