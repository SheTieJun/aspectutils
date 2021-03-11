package me.shetj.aspect.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class NetworkAspect {

	@Pointcut("execution(@me.shetj.aspect.network.CheckNetwork * *(..)) && @annotation(checkNetwork)")
	public void checkNetworkMethod(CheckNetwork checkNetwork) {
	}


	@Around("checkNetworkMethod(checkNetwork)")
	public void beforeCheckNetworkMethod(ProceedingJoinPoint joinPoint,CheckNetwork checkNetwork) throws Throwable {
		boolean needNet = checkNetwork.isNeedNet();
		if (needNet){
		    try {
                ConnectivityManager manager = (ConnectivityManager)((Context)joinPoint.getThis()).getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                boolean	hasInternet = manager != null && manager.getActiveNetworkInfo() != null;
                if (hasInternet) {
                    joinPoint.proceed();
                }else {
                    Log.i("NetworkAspect","暂无网络~！");
                }
            }catch (Exception e){
		        e.printStackTrace();
            }
		}else {
			joinPoint.proceed();
		}
	}
}
