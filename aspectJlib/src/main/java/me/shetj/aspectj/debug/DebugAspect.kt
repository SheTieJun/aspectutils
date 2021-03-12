package me.shetj.aspectj.debug

import android.util.Log
import me.shetj.aspectj.kit.TAG
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

/**
 * **@packageName：** me.shetj.aspectutils<br></br>
 * **@author：** shetj<br></br>
 * **@createTime：** 2018/8/29 0029<br></br>
 * **@company：**<br></br>
 * **@email：** 375105540@qq.com<br></br>
 * **@describe**<br></br>
 */
@Aspect
class DebugAspect {
    @Pointcut("execution(@me.shetj.aspect.debug.DebugTrace * *())")
    fun DebugTraceMethod() {
    }

    @Around("DebugTraceMethod()")
    @Throws(Throwable::class)
    fun beforeDebugTraceMethod(joinPoint: ProceedingJoinPoint) {
        val methodName = joinPoint.signature.name
        Log.i(TAG, methodName + " start time = " + System.currentTimeMillis())
        //		// 目标类全路径名
        val targetName = joinPoint.target.javaClass.name
        Log.i(TAG, "  : getSimpleName = " + joinPoint.getThis().javaClass.simpleName)
        // 反射得到目标类
//		Class clazz = Class.forName(targetName);
// 目标方法名（正在访问的方法）
        Log.i(TAG, "  : targetName = $targetName")
        // 方法参数：数组类型
//		Object[] arguments = joinPoint.getArgs();
//        Log.i("DebugAspect", "   : arguments =  " + arguments);
//// 反射得到目标类的所有方法
//		Method[] methods = clazz.getMethods();
//		Timber.i("   : arguments =  " + GsonKit.objectToJson(methods));
        joinPoint.proceed()
        Log.i(TAG, methodName + " end time = " + System.currentTimeMillis())
    }
}