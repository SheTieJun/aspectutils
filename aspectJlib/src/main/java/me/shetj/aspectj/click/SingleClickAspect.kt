package me.shetj.aspectj.click

import android.view.View
import me.shetj.aspectj.R
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
class SingleClickAspect {
    @Pointcut("execution(@me.shetj.aspect.click.SingleClick * *(..)) && @annotation(singleClick)")
    fun onSingClickMethod(singleClick: SingleClick?) {
    }

    @Around("onSingClickMethod(singleClick)") //连接点替换
    fun doSingleClickMethod(joinPoint: ProceedingJoinPoint, singleClick: SingleClick) {
        var view: View? = null
        for (arg in joinPoint.args) {
            if (arg is View) view = arg
        }
        if (view != null) {
            val tag = view.getTag(TIME_TAG)
            val lastClickTime = if (tag != null) tag as Long else 0
            val currentTime = System.currentTimeMillis()
            val value: Long = singleClick.value
            if (currentTime - lastClickTime > value) {
                view.setTag(TIME_TAG, currentTime)
                try {
                    joinPoint.proceed()
                } catch (throwable: Throwable) {
                    throwable.printStackTrace()
                }
            }
        }
    }

    companion object {
        private val TIME_TAG = R.id.time_tag
    }
}