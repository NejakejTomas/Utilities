package cz.nejakejtomas.kmp.utils.compose.events

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// https://www.youtube.com/watch?v=njchj9d_Lf8
@Suppress("unused")
@Composable
fun <T> EventEmitter<T>.Observe(observer: EventObserver<T>) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner.lifecycle, this) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect(observer::emit)
            }
        }
    }
}