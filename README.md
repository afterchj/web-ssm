# web-ssm
springmvc学习笔记
1.<aop:pointcut>如果位于<aop:aspect>元素中，则命名切点只能被当前<aop:aspect>内定义的元素访问到，为了能被整个<aop:config>元素中定义的所有增强访问，则必须在<aop:config>下定义切点。
2.如果在<aop:config>元素下直接定义<aop:pointcut>，必须保证<aop:pointcut>在<aop:aspect>之前定义。<aop:config>下还可以定义<aop:advisor>，三者在<aop:config>中的配置有先后顺序的要求：首先必须是<aop:pointcut>，然后是<aop:advisor>，最后是<aop:aspect>。而在<aop:aspect>中定义的<aop:pointcut>则没有先后顺序的要求，可以在任何位置定义。
.<aop:pointcut>：用来定义切入点，该切入点可以重用；
.<aop:advisor>：用来定义只有一个通知和一个切入点的切面；
.<aop:aspect>：用来定义切面，该切面可以包含多个切入点和通知，而且标签内部的通知和切入点定义是无序的；和advisor的区别就在此，advisor只包含一个通知和一个切入点。
3.在使用spring框架配置AOP的时候，不管是通过XML配置文件还是注解的方式都需要定义pointcut"切入点"
例如定义切入点表达式 execution(* com.sample.service.impl..*.*(..))
execution()是最常用的切点函数，其语法如下所示：
整个表达式可以分为五个部分：  

(1)、execution(): 表达式主体。  

(2)、第一个*号：表示返回类型，*号表示所有的类型。  

(3)、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。  

(4)、第二个*号：表示类名，*号表示所有的类。  

(5)、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。  

