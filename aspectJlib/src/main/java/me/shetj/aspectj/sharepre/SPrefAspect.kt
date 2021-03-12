package me.shetj.aspectj.sharepre

import android.app.Activity
import android.util.Log
import me.shetj.aspectj.kit.SPUtils.Companion.gson
import me.shetj.aspectj.kit.SPUtils.Companion.put
import me.shetj.aspectj.kit.TAG
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.json.JSONObject

@Aspect
class SPrefAspect {
    @Pointcut("execution(@me.shetj.aspectj.sharepre.SPrefs * *(..))")
    fun onSPMethod() {
    }

    @Around("onSPMethod() && @annotation(sp) ")
    fun doSPMethod(joinPoint: ProceedingJoinPoint, sp: SPrefs): Any? {
        if (joinPoint.getThis() !is Activity) {
            Log.e(TAG, "@SPrefs only supports the Activity")
            return  joinPoint.proceed()
        }
        try {
            val key: String = sp.key //保存的key
            return if (key == null || key.isEmpty()) {
                joinPoint.proceed()
            } else {
                val result = joinPoint.proceed() //方法执行的结果
                //获取方法返回类型
                val type = (joinPoint.signature as MethodSignature).returnType.toString()
                val json  = gson.toJson(result)
                Log.i(TAG, json)
                if (joinPoint.getThis() is Activity) {
                    val activity = joinPoint.getThis() as Activity
                    if ("void" != type && json != null && json.isNotEmpty()) {
                        put(activity, key, json)
                    }
                }
                result
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }
        return null
    }
}