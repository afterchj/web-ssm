package test;

import com.tpadsz.after.aop02.StrUtil;
import com.tpadsz.after.aop01.Calculate;
import com.tpadsz.after.aop02.MathService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hongjian.chen on 2019/1/2.
 */
public class MyTest {


    @Test
    public void test1() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:aop01.xml");
        Calculate math = ctx.getBean("math", Calculate.class);
        int n1 = 100, n2 = 0;
        math.add(n1, n2);
        math.subtract(n1, n2);
        math.mutiply(n1, n2);
        math.divide(n1, n2);
    }

    @Test
    public void test2() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:aop02.xml");
        MathService mathService = ctx.getBean("mathService", MathService.class);
        System.out.println("mathService="+mathService);
        int n1 = 100, n2 = 4;
        mathService.add(n1, n2);
        mathService.subtract(n1, n2);
        mathService.multiply(n1, n2);
        mathService.divide(n1, n2);

        StrUtil strUtil= (StrUtil) ctx.getBean("strUtil");
        strUtil.show();
    }

}
