package cz.nejakejtomas.kmp.utils.core.events

import cz.nejakejtomas.kmp.utils.core.runSuspendCatching

internal actual fun platformOrigin() = Throwable()

internal actual fun Throwable.attachOrigin(origin: Throwable) {
    if (stackTrace.isEmpty()) return

    runSuspendCatching {
        // Crashlytics ignores suppressed exceptions
        val current = Throwable().stackTrace
        val shared =
            stackTrace.reversed().zip(current.reversed()).takeWhile { (a, b) -> a == b }.size
        stackTrace = (stackTrace.dropLast(shared) + origin.stackTrace).toTypedArray()
    }
}