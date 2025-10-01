package cz.nejakejtomas.kmp.utils.compose.permissions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
@Suppress("unused")
fun rememberAlwaysGrantedPermissionState(
    permission: String = "",
    onPermissionResult: (Boolean) -> Unit = {}
): PermissionState {
    return remember { AlwaysGrantedPermissionState(permission, onPermissionResult) }
}