package cz.nejakejtomas.kmp.utils.viewmodel

import androidx.lifecycle.SavedStateHandle

private class StartedKey

private val STARTED_KEY: String = StartedKey::class.qualifiedName!!

@Suppress("unused")
suspend fun SavedStateHandle.ifNotRecreating(block: suspend () -> Unit) {
    if (!contains(STARTED_KEY)) block()
    set(STARTED_KEY, null)
}

@Suppress("unused")
fun SavedStateHandle.ifNotRecreating(block: () -> Unit) {
    if (!contains(STARTED_KEY)) block()
    set(STARTED_KEY, null)
}