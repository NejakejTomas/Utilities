@file:OptIn(ExperimentalTypeInference::class)
@file:Suppress("UNCHECKED_CAST")

package cz.nejakejtomas.kmp.utils.core.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlin.experimental.ExperimentalTypeInference
import kotlinx.coroutines.flow.combine as kotlinCombine

context(scope: CoroutineScope)
fun <T> Flow<T>.asStateFlow(initialValue: T): StateFlow<T> =
    stateIn(scope = scope, started = SharingStarted.Eagerly, initialValue)

@PublishedApi
internal inline fun <reified T, R> combineInternal(
    vararg flows: Flow<T>,
    crossinline transform: (Array<T>) -> R
): Flow<R> = kotlinCombine(flows = flows, transform = transform)

@Suppress("unused")
inline fun <reified T, R> combine(
    vararg flows: Flow<T>,
    @BuilderInference crossinline transform: (Array<T>) -> R
): Flow<R> = combineInternal<T, R>(flows = flows, transform = transform)

@Suppress("unused")
fun <T1, R> CoroutineScope.combine(
    flow1: StateFlow<T1>,
    @BuilderInference transform: (a: T1) -> R
): StateFlow<R> = combineInternal<Any?, R>(flow1, transform = { args: Array<*> ->
    transform(
        args[0] as T1,
    )
}).asStateFlow(transform(flow1.value))

@Suppress("unused")
fun <T1, T2, R> CoroutineScope.combine(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    @BuilderInference transform: (a: T1, b: T2) -> R
): StateFlow<R> = combineInternal<Any?, R>(flow1, flow2, transform = { args: Array<*> ->
    transform(
        args[0] as T1,
        args[1] as T2,
    )
}).asStateFlow(transform(flow1.value, flow2.value))

@Suppress("unused")
fun <T1, T2, T3, R> CoroutineScope.combine(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    @BuilderInference transform: (a: T1, b: T2, c: T3) -> R
): StateFlow<R> = combineInternal<Any?, R>(flow1, flow2, flow3, transform = { args: Array<*> ->
    transform(
        args[0] as T1,
        args[1] as T2,
        args[2] as T3,
    )
}).asStateFlow(transform(flow1.value, flow2.value, flow3.value))

@Suppress("unused")
fun <T1, T2, T3, T4, R> CoroutineScope.combine(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    flow4: StateFlow<T4>,
    @BuilderInference transform: (a: T1, b: T2, c: T3, d: T4) -> R
): StateFlow<R> =
    combineInternal<Any?, R>(flow1, flow2, flow3, flow4, transform = { args: Array<*> ->
        transform(
            args[0] as T1,
            args[1] as T2,
            args[2] as T3,
            args[3] as T4,
        )
    }).asStateFlow(transform(flow1.value, flow2.value, flow3.value, flow4.value))

@Suppress("unused")
fun <T1, T2, T3, T4, T5, R> CoroutineScope.combine(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    flow4: StateFlow<T4>,
    flow5: StateFlow<T5>,
    @BuilderInference transform: (a: T1, b: T2, c: T3, d: T4, e: T5) -> R
): StateFlow<R> =
    combineInternal<Any?, R>(flow1, flow2, flow3, flow4, flow5, transform = { args: Array<*> ->
        transform(
            args[0] as T1,
            args[1] as T2,
            args[2] as T3,
            args[3] as T4,
            args[4] as T5,
        )
    }).asStateFlow(transform(flow1.value, flow2.value, flow3.value, flow4.value, flow5.value))

@Suppress("unused")
fun <T1, T2, T3, T4, T5, T6, R> CoroutineScope.combine(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    flow4: StateFlow<T4>,
    flow5: StateFlow<T5>,
    flow6: StateFlow<T6>,
    @BuilderInference transform: (a: T1, b: T2, c: T3, d: T4, e: T5, f: T6) -> R
): StateFlow<R> = combineInternal<Any?, R>(
    flow1,
    flow2,
    flow3,
    flow4,
    flow5,
    flow6,
    transform = { args: Array<*> ->
        transform(
            args[0] as T1,
            args[1] as T2,
            args[2] as T3,
            args[3] as T4,
            args[4] as T5,
            args[5] as T6,
        )
    }).asStateFlow(
    transform(
        flow1.value,
        flow2.value,
        flow3.value,
        flow4.value,
        flow5.value,
        flow6.value
    )
)

@Suppress("unused")
fun <T1, T2, T3, T4, T5, T6, T7, R> CoroutineScope.combine(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    flow4: StateFlow<T4>,
    flow5: StateFlow<T5>,
    flow6: StateFlow<T6>,
    flow7: StateFlow<T7>,
    @BuilderInference transform: (a: T1, b: T2, c: T3, d: T4, e: T5, f: T6, g: T7) -> R
): StateFlow<R> = combineInternal<Any?, R>(
    flow1,
    flow2,
    flow3,
    flow4,
    flow5,
    flow6,
    flow7,
    transform = { args: Array<*> ->
        transform(
            args[0] as T1,
            args[1] as T2,
            args[2] as T3,
            args[3] as T4,
            args[4] as T5,
            args[5] as T6,
            args[6] as T7,
        )
    }).asStateFlow(
    transform(
        flow1.value,
        flow2.value,
        flow3.value,
        flow4.value,
        flow5.value,
        flow6.value,
        flow7.value
    )
)

@Suppress("unused")
fun <T1, T2, T3, T4, T5, T6, T7, T8, R> CoroutineScope.combine(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    flow4: StateFlow<T4>,
    flow5: StateFlow<T5>,
    flow6: StateFlow<T6>,
    flow7: StateFlow<T7>,
    flow8: StateFlow<T8>,
    @BuilderInference transform: (a: T1, b: T2, c: T3, d: T4, e: T5, f: T6, g: T7, h: T8) -> R
): StateFlow<R> = combineInternal<Any?, R>(
    flow1,
    flow2,
    flow3,
    flow4,
    flow5,
    flow6,
    flow7,
    flow8,
    transform = { args: Array<*> ->
        transform(
            args[0] as T1,
            args[1] as T2,
            args[2] as T3,
            args[3] as T4,
            args[4] as T5,
            args[5] as T6,
            args[6] as T7,
            args[7] as T8,
        )
    }).asStateFlow(
    transform(
        flow1.value,
        flow2.value,
        flow3.value,
        flow4.value,
        flow5.value,
        flow6.value,
        flow7.value,
        flow8.value
    )
)

@Suppress("unused")
fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> CoroutineScope.combine(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    flow4: StateFlow<T4>,
    flow5: StateFlow<T5>,
    flow6: StateFlow<T6>,
    flow7: StateFlow<T7>,
    flow8: StateFlow<T8>,
    flow9: StateFlow<T9>,
    @BuilderInference transform: (a: T1, b: T2, c: T3, d: T4, e: T5, f: T6, g: T7, h: T8, i: T9) -> R
): StateFlow<R> = combineInternal<Any?, R>(
    flow1,
    flow2,
    flow3,
    flow4,
    flow5,
    flow6,
    flow7,
    flow8,
    flow9,
    transform = { args: Array<*> ->
        transform(
            args[0] as T1,
            args[1] as T2,
            args[2] as T3,
            args[3] as T4,
            args[4] as T5,
            args[5] as T6,
            args[6] as T7,
            args[7] as T8,
            args[8] as T9,
        )
    }).asStateFlow(
    transform(
        flow1.value,
        flow2.value,
        flow3.value,
        flow4.value,
        flow5.value,
        flow6.value,
        flow7.value,
        flow8.value,
        flow9.value
    )
)

@Suppress("unused")
fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> CoroutineScope.combine(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    flow4: StateFlow<T4>,
    flow5: StateFlow<T5>,
    flow6: StateFlow<T6>,
    flow7: StateFlow<T7>,
    flow8: StateFlow<T8>,
    flow9: StateFlow<T9>,
    flow10: StateFlow<T10>,
    @BuilderInference transform: (a: T1, b: T2, c: T3, d: T4, e: T5, f: T6, g: T7, h: T8, i: T9, j: T10) -> R
): StateFlow<R> = combineInternal<Any?, R>(
    flow1,
    flow2,
    flow3,
    flow4,
    flow5,
    flow6,
    flow7,
    flow8,
    flow9,
    flow10,
    transform = { args: Array<*> ->
        transform(
            args[0] as T1,
            args[1] as T2,
            args[2] as T3,
            args[3] as T4,
            args[4] as T5,
            args[5] as T6,
            args[6] as T7,
            args[7] as T8,
            args[8] as T9,
            args[9] as T10,
        )
    }).asStateFlow(
    transform(
        flow1.value,
        flow2.value,
        flow3.value,
        flow4.value,
        flow5.value,
        flow6.value,
        flow7.value,
        flow8.value,
        flow9.value,
        flow10.value
    )
)