package me.shetj.aspect.kit

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


/**
 * 针对6.0动态请求权限问题,判断是否允许此权限
 *  可以使用 [AppCompatActivity.registerForActivityResult] 替代
 *  registerForActivityResult(ActivityResultContracts.RequestPermission())
 */
fun AppCompatActivity.hasPermission(vararg permissions: String, isRequest: Boolean = true): Boolean {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true
    val permissionsCheck: MutableList<String> = ArrayList()
    for (permission in permissions) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsCheck.add(permission)
        }
    }
    if (permissionsCheck.isEmpty()) return true
    if (isRequest) {
        ActivityCompat.requestPermissions(this, permissionsCheck.toTypedArray(), 100)
    }
    return false
}





