package cz.nejakejtomas.kmp.utils.core.events

@RequiresOptIn(
    message = "Stack trace will not show where the event was sent",
    level = RequiresOptIn.Level.WARNING,
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class DestroysStackTrace