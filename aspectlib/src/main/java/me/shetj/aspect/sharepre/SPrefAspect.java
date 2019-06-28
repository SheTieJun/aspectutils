package me.shetj.aspect.sharepre;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import me.shetj.base.tools.app.Utils;
import me.shetj.base.tools.file.SPUtils;
import me.shetj.base.tools.json.EmptyUtils;
import me.shetj.base.tools.json.GsonKit;
import timber.log.Timber;

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
            if (EmptyUtils.isEmpty(key)){
              return   joinPoint.proceed();
            }else {
                Object result = joinPoint.proceed(); //方法执行的结果
                //获取方法返回类型
                String type = ((MethodSignature) joinPoint.getSignature()).getReturnType().toString();

                String json = GsonKit.objectToJson(result);

//                //保存结果
                if (!"void".equals(type) && EmptyUtils.isNotEmpty(json)) {
                    SPUtils.put(Utils.getApp(),key,json);
                }
                return result;
            }
        } catch (Throwable throwable) {
            Timber.e(throwable);
        }
        return null;
    }
}
