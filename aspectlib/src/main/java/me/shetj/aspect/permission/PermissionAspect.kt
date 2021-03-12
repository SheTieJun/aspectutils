package me.shetj.aspect.permission

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import me.shetj.aspect.kit.SPUtils.Companion.put
import me.shetj.aspect.kit.TAG
import me.shetj.aspect.kit.hasPermission
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import java.util.*

@Aspect
class PermissionAspect {

    @Pointcut("execution(@me.shetj.aspect.permission.MPermission * *(..)) && @annotation(permission)")
    fun methodAnnotatedWithMPermission(permission: MPermission?) {
    }

    @Around("methodAnnotatedWithMPermission(permission)")
    @Throws(Throwable::class)
    fun checkPermission(joinPoint: ProceedingJoinPoint, permission: MPermission) {

        if (joinPoint.getThis() !is Activity) {
            Log.e(TAG, "@MPermission only supports the Activity")
            return
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            try {
                joinPoint.proceed()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
            return
        }
        val permissionStr: Array<String> = permission.value
        val length = permissionStr.size
        if (length == 0) return
        Log.i(TAG, permissionStr.joinToString(" , ", "[", "]"))
        val activity = joinPoint.getThis() as AppCompatActivity
        val aBoolean = activity.hasPermission(*permissionStr, isRequest = permission.isRequest)
        if (aBoolean) {
            try {
                joinPoint.proceed()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        } else {
            Log.e(TAG, "没有对应权限，无法使用该功能~！")
        }
    }
}