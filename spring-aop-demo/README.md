# 打包命令
### mvn package -Dmaven.test.skip=true
spring-aop学习笔记  
在使用spring框架配置AOP的时候，不管是通过XML配置文件还是注解的方式都需要定义pointcut"切入点"  
例如定义切入点表达式 `execution(* com.sample.service.impl..*.*(..))` execution()是最常用的切点函数，其语法如下所示：整个表达式可以分为五个部分：    
1. execution(): 表达式主体。   
2. 第一个*号：表示返回类型，*号表示所有的类型。   
3. 包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。    
4. 第二个*号：表示类名，*号表示所有的类。   
5. *(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。    

