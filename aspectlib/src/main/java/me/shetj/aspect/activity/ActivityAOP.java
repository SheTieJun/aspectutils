package me.shetj.aspect.activity;

import android.content.Intent;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ActivityAOP {
    private static final String TAG = ActivityAOP.class.getSimpleName();

    /**
     * activity onCreate point cut
     */
    @Pointcut("execution(* android.app.Activity.onCreate(..))")
    public void activityOnCreate() {
    }


    @Around("activityOnCreate()")
    public void activityOnCreateTriggered(ProceedingJoinPoint joinPoint) throws Throwable{
                String targetClassName = joinPoint.getTarget().getClass().getName();
        String signatureName = joinPoint.getSignature().getName();
        Log.d(TAG, targetClassName + " " + signatureName + " Around");
        joinPoint.proceed();
    }


    @Pointcut("execution(* android.app.Activity.onResume())")
    public void activityOnResume() {

    }

    @Before("activityOnResume()")
    public void activityOnResumeTriggered(JoinPoint joinPoint) {
        String targetClassName = joinPoint.getTarget().getClass().getSimpleName();
        String signatureName = joinPoint.getSignature().getName();
        Log.d(TAG, targetClassName + " " + signatureName + " Before");
    }



    @Pointcut("execution(* android..*.startActivity*(..))")
    public void activityOnStartActivity(){

    }


    @Before("activityOnStartActivity()")
    public void activityOnStartActivityTriggered(JoinPoint joinPoint) {
        String targetClassName = joinPoint.getTarget().getClass().getName();
        String signatureName = joinPoint.getSignature().getName();
        Log.d(TAG, targetClassName + " " + signatureName + " before：" +joinPoint.getThis().getClass().getSimpleName());

        Object[] args = joinPoint.getArgs();

        if (args != null && args.length > 0  ) {
           Intent intent = (Intent) args[0];
           intent.putExtra(Constant.EXTRE_FROME,joinPoint.getThis().getClass().getSimpleName());
        }
    }
}