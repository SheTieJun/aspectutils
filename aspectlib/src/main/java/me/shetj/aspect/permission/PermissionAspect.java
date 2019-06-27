package me.shetj.aspect.permission;


import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import me.shetj.base.tools.app.ArmsUtils;

@Aspect
public class PermissionAspect {
	@Pointcut("execution(@me.shetj.aspect.permission.MPermission * *(..)) && @annotation(permission)")
	public void methodAnnotatedWithMPermission(MPermission permission) {}

	@Around("methodAnnotatedWithMPermission(permission)")
	public void checkPermission(final ProceedingJoinPoint joinPoint, MPermission permission) throws Throwable {
		String[] permissionStr = permission.value();
		RxPermissions	rxPermissions = new RxPermissions((FragmentActivity) joinPoint.getThis());
		rxPermissions.request(permissionStr)
						.subscribe(aBoolean -> {
							if (aBoolean) {
								try {
									joinPoint.proceed();
								} catch (Throwable throwable) {
									throwable.printStackTrace();
								}
							} else {
								ArmsUtils.Companion.makeText("没有对应权限，无法使用该功能~！");
							}
						});
	}
}