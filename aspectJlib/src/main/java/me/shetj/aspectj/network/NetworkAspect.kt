package me.shetj.aspectj.network

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import me.shetj.aspectj.kit.TAG
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
class NetworkAspect {
    @Pointcut("execution(@me.shetj.aspectj.network.CheckNetwork * *(..)) && @annotation(checkNetwork)")
    fun checkNetworkMethod(checkNetwork: CheckNetwork?) {
    }

    @Around("checkNetworkMethod(checkNetwork)")
    @Throws(Throwable::class)
    fun beforeCheckNetworkMethod(joinPoint: ProceedingJoinPoint, checkNetwork: CheckNetwork):Any? {
        if (joinPoint.getThis() !is Activity ) {
            Log.e(TAG, "@CheckNetwork only supports the Activity")
            return null
        }
        val needNet: Boolean = checkNetwork.isNeedNet
        if (needNet) {
            try {
                val manager = (joinPoint.getThis() as Context).applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val hasInternet = manager.activeNetworkInfo != null
                if (hasInternet) {
                    return  joinPoint.proceed()
                } else {
                    Log.i(TAG, "暂无网络~！")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            return  joinPoint.proceed()
        }
        return null
    }
}