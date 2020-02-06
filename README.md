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

### 使用

由于部分依赖包是使用base,所以需要使用base

```
implementation "com.github.SheTieJun:Base:$base_kt_version"
implementation 'com.github.SheTieJun:aspectutils:12004150ad'
```

```
execution	过滤出方法执行时的连接点
within	过滤出制定类型内方法
this	过滤当前AOP对象的执行时方法
target	过滤目标对象的执行时方法
args	过滤出方法执行时参数匹配args的方法
@within	过滤出持有指定注解类型内的方法
@target	过滤目标对象持有指定注解类型的方法
@args	过滤当前执行的传入的参数持有指定注解的方法
@annotation	过滤当前执行的持有指定注解的方法

```

