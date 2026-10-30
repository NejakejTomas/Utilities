package cz.nejakejtomas.kmp.utils.compose.koin

import androidx.compose.runtime.retain.RetainObserver
import org.koin.core.scope.Scope

@PublishedApi
internal class RetainedKoinScope(
    val scope: Scope
) : RetainObserver {
    override fun onRetained() = Unit
    override fun onEnteredComposition() = Unit
    override fun onExitedComposition() = Unit

    override fun onRetired() {
        scope.logger.debug("${RetainedKoinScope::class.simpleName} onRetired: '${scope.id}' ($this)")
        close()
    }

    override fun onUnused() {
        scope.logger.debug("${RetainedKoinScope::class.simpleName} onUnused: '${scope.id}' ($this)")
        close()
    }

    private fun close() {
        if (!scope.isRoot && !(scope.closed)) {
            scope.logger.debug("${RetainedKoinScope::class.simpleName} close scope: '${scope.id}' ($this)")
            scope.close()
        }
    }
}