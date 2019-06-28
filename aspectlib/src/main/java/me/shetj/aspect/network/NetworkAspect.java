package me.shetj.aspect.network;

import android.content.Context;
import android.net.ConnectivityManager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import me.shetj.base.tools.app.ArmsUtils;
import me.shetj.base.tools.app.Utils;

/**
 * <b>@packageName：</b> me.shetj.aspectutils<br>
 * <b>@author：</b> shetj<br>
 * <b>@createTime：</b> 2018/8/29 0029<br>
 * <b>@company：</b><br>
 * <b>@email：</b> 375105540@qq.com<br>
 * <b>@describe</b><br>
 */
@Aspect
public class NetworkAspect {

	@Pointcut("execution(@me.shetj.aspect.network.CheckNetwork * *(..)) && @annotation(checkNetwork)")
	public void checkNetworkMethod(CheckNetwork checkNetwork) {
	}


	@Around("checkNetworkMethod(checkNetwork)")
	public void beforeCheckNetworkMethod(ProceedingJoinPoint joinPoint,CheckNetwork checkNetwork) throws Throwable {
		boolean needNet = checkNetwork.isNeedNet();
		if (needNet){
			ConnectivityManager manager = (ConnectivityManager) Utils.getApp().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
			boolean	hasInternet = manager != null && manager.getActiveNetworkInfo() != null;
			if (hasInternet) {
				joinPoint.proceed();
			}else {
				ArmsUtils.Companion.makeText("暂无网络~！");
			}
		}else {
			joinPoint.proceed();
		}
	}
}
