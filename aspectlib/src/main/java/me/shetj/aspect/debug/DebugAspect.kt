package me.shetj.aspect.debug;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * <b>@packageName：</b> me.shetj.aspectutils<br>
 * <b>@author：</b> shetj<br>
 * <b>@createTime：</b> 2018/8/29 0029<br>
 * <b>@company：</b><br>
 * <b>@email：</b> 375105540@qq.com<br>
 * <b>@describe</b><br>
 */
@Aspect
public class DebugAspect {

	@Pointcut("execution(@me.shetj.aspect.debug.DebugTrace * *())")
	public void DebugTraceMethod() {
	}


	@Around("DebugTraceMethod()")
	public void beforeDebugTraceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName();
		Log.i("DebugAspect", methodName+" start time = " + System.currentTimeMillis());
//		// 目标类全路径名
		String targetName = joinPoint.getTarget().getClass().getName();
        Log.i("DebugAspect", "  : getSimpleName = " + joinPoint.getThis().getClass().getSimpleName());
		// 反射得到目标类
//		Class clazz = Class.forName(targetName);
// 目标方法名（正在访问的方法）
        Log.i("DebugAspect", "  : targetName = " + targetName);
// 方法参数：数组类型
//		Object[] arguments = joinPoint.getArgs();
//        Log.i("DebugAspect", "   : arguments =  " + arguments);
//// 反射得到目标类的所有方法
//		Method[] methods = clazz.getMethods();
//		Timber.i("   : arguments =  " + GsonKit.objectToJson(methods));
		joinPoint.proceed();
        Log.i("DebugAspect", methodName+ " end time = " +  System.currentTimeMillis());
	}

}
