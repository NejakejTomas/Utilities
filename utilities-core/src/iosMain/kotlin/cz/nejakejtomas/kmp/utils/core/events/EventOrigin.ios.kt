package cz.nejakejtomas.kmp.utils.core.events

internal actual fun platformOrigin() = Throwable("Event was sent here")

internal actual fun Throwable.attachOrigin(origin: Throwable) {
    // Fixme: This is probably not visible in Firebase
    addSuppressed(origin)
}