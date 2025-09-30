package cz.nejakejtomas.kmp.utils.compose.events

import kotlinx.coroutines.flow.Flow

sealed class EventEmitter<out T> {
    internal abstract val flow: Flow<T>
}