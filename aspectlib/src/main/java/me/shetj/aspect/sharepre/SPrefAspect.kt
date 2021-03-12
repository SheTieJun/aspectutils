package me.shetj.aspect.sharepre;


import android.app.Activity;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;

import me.shetj.aspect.kit.SPUtils;

@Aspect
public class SPrefAspect {
    private final String POINT_CUT = "execution(@me.shetj.aspect.sharepre.SPrefs * *(..))";

    @Pointcut(POINT_CUT)
    public void onSPMethod(){

    }


    @Around("onSPMethod() && @annotation(sp) ")
    public Object doSPMethod(ProceedingJoinPoint joinPoint,SPrefs sp){
        try {
            String key = sp.key(); //保存的key
            if (key == null || key.isEmpty()){
              return  joinPoint.proceed();
            }else {
                Object result = joinPoint.proceed(); //方法执行的结果
                //获取方法返回类型
                String type = ((MethodSignature) joinPoint.getSignature()).getReturnType().toString();
                JSONObject object = new JSONObject();
                object.put("result",result);
                String json = object.toString();
                Log.i("SPrefAspect", json);
                if (joinPoint.getThis() instanceof  Activity){
                    Activity activity = ((Activity)joinPoint.getThis());
                    if (!"void".equals(type) && json != null && !json.isEmpty()) {
                        SPUtils.put(activity,key,json);
                    }
                }
                return result;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
