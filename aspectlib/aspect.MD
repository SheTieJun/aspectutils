#### DebugAspect 计算时间

#### MPermission 获取权限

#### CheckNetWork 检查网络

#### SingleClick 防止连续点击

### 后续：

- [ ] 拦截

- [ ] 异步

- [ ] 缓存

- [ ] 延迟

- [x] SP sharePre

- [x] 单击，防抖动

- [x] 网络判断

  



```
Call（Before）
Pointcut{
    Pointcut Method
}
Call（After）

对于Execution来说：

Pointcut{
  execution（Before）
    Pointcut Method
  execution（After）
}




```

- 使用注解来标记切入点，也可通过一些方法

**对 实现具体class 使用 @Aspect 标注**

```
execution   用于匹配方法执行的连接点 

within 用于匹配指定类型内的方法执行

this  用于匹配当前AOP代理对象类型的执行方法；注意是AOP代理对象的类型匹配，这就可能包括引入接口也类型匹配

target 用于匹配当前目标对象类型的执行方法；注意是目标对象的类型匹配，这样就不包括引入接口也类型匹配

args   用于匹配当前执行的方法传入的参数为指定类型的执行方法

@within    用于匹配所以持有指定注解类型内的方法
@target    用于匹配当前目标对象类型的执行方法，其中目标对象持有指定的注解

@args  用于匹配当前执行的方法传入的参数持有指定注解的执行

@annotation    用于匹配当前执行方法持有指定注解的方法
```

```
joinPoint.getArgs()  获取切入方法的参数
```

```
joinPoint.getThis()  或者自己  可能是activity 也可能是其他类
```

```
- java.lang.Object[] getArgs()：获取连接点方法运行时的入参列表；
- Signature getSignature() ：获取连接点的方法签名对象；
- java.lang.Object getTarget() ：获取连接点所在的目标对象；
- java.lang.Object getThis() ：获取代理对象本身； 
```







介绍使用

https://www.jianshu.com/p/c3a4336a0f29?utm_source=oschina-app




### 记录错误
- formal unbound in pointcut 
```
@Pointcut("execution(@me.shetj.aspect.network.CheckNetwork * *(..) ")
```
修改后
```
@Pointcut("execution(@me.shetj.aspect.network.CheckNetwork * *(..)) && @annotation(checkNetwork)")
```





- org.aspectj.weaver.patterns.ParserException: pointcut name at position 1

  不小心写错 了，是说你第一位就错了

  ```
  @Pointcut("execution(@me.shetj.aspect.network.CheckNetwork ..(..)) 
  ```

  ```
  Pointcut("execution(@me.shetj.aspect.network.CheckNetwork * *(..)) 
  ```

- applying to join point that doesn't return void: method-execution(java.lang.String me.shetj.aspectutils.aspect.AspectPresenter.testSPrefs())

  切入的方法有返回，但是我忘记返回了。要保持一直

### Invalid byte tag in constant pool: 19
```
解决方案： 
aspectjx {
//关闭AspectJX功能
enabled true
//进行aop处理范围是当前项目包路径
include 'com.company.package'
}
```