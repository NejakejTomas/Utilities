package cz.nejakejtomas.kmp.utils.compose.events

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import cz.nejakejtomas.kmp.utils.core.events.EventEmitter
import cz.nejakejtomas.kmp.utils.core.events.EventObserver

@Suppress("unused")
@Composable
fun <T> EventEmitter<T>.Observe(observer: EventObserver<T>) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner.lifecycle, this) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            observe(observer)
        }
    }
}