package cz.nejakejtomas.kmp.utils.core

import kotlin.coroutines.cancellation.CancellationException

@Suppress("unused")
inline fun <R> runSuspendCatching(onCancel: (() -> Unit) = {}, block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        if (e is CancellationException) {
            try {
                onCancel()
            } catch (t: Throwable) {
                e.addSuppressed(t)
            }
            throw e
        }

        Result.failure(e)
    }
}

@Suppress("unused", "NOTHING_TO_INLINE", "FunctionName")
inline fun NoOp() {
    // No-op
}

@Suppress("unused", "NOTHING_TO_INLINE", "FunctionName")
inline fun NoOp(reason: String) {
    // No-op
}