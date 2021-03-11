package me.shetj.aspect.permission;


import android.os.Build;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import me.shetj.aspect.kit.ActivityExtKt;


@Aspect
public class PermissionAspect {
	@Pointcut("execution(@me.shetj.aspect.permission.MPermission * *(..)) && @annotation(permission)")
	public void methodAnnotatedWithMPermission(MPermission permission) {}

    @Around("methodAnnotatedWithMPermission(permission)")
	public void checkPermission(final ProceedingJoinPoint joinPoint, MPermission permission) throws Throwable {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            try {
                joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return;
        }

		String[] permissionStr = permission.value();
		boolean aBoolean = true;
        int length = permissionStr.length;
        if (length == 0) return ;

		StringBuilder buffer = new StringBuilder();
		for (String s : permissionStr) {
		    buffer.append(s);
            buffer.append("|");
		}
		Log.i("PermissionAspect",buffer.toString());
        AppCompatActivity activity = ((AppCompatActivity)joinPoint.getThis());
        aBoolean = ActivityExtKt.hasPermission(activity,permissionStr,permission.isRequest());
        if (aBoolean) {
            try {
                joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } else {
            Log.e("PermissionAspect","没有对应权限，无法使用该功能~！");
        }
	}
}