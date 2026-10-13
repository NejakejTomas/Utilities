package cz.nejakejtomas.kmp.utils.core.events

internal expect fun platformOrigin(): Throwable

internal expect fun Throwable.attachOrigin(origin: Throwable)