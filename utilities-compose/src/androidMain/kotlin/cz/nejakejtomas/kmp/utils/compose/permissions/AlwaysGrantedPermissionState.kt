package cz.nejakejtomas.kmp.utils.compose.permissions

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus

@OptIn(ExperimentalPermissionsApi::class)
internal class AlwaysGrantedPermissionState(
    override val permission: String = "",
    private val onResult: (Boolean) -> Unit = {}
) : PermissionState {
    override val status = PermissionStatus.Granted
    override fun launchPermissionRequest() = onResult(true)
}