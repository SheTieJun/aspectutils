# aspectutils
develop and collect some scene to use aop

借鉴：
https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx

```
   classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.4'
```

app：build.gradle
```

apply plugin: 'android-aspectjx'//面向切面

aspectjx {
    exclude 'androidx.*'
}
```
如果也想写自己独特的
```
   implementation 'org.aspectj:aspectjrt:1.8.14'
```


#### DebugAspect

计算方法执行时间

#### MPermission

获取权限

#### CheckNetWork

检查网络

#### SingleClick

防止连续点击

#### SPrefs

保存到SP中 


##### 

```
  implementation 'com.github.SheTieJun:Base:12004150ad'
```


