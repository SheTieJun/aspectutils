# aspectutils
develop and collect some scene to use aop

https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx

```
   classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.2'
```


```
apply plugin: 'android-aspectjx'//面向切面

aspectjx {
    exclude 'android.support'
}

```



##### DebugAspect
计算时间

##### MPermission
获取权限