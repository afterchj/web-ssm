package test.config;

import com.tpadsz.after.aop03.ApplicationCfg;
import com.tpadsz.after.aop03.MathService03;
import com.tpadsz.after.aop03.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by hongjian.chen on 2019/1/2.
 */
public class ConfigurationTest {

    public static void main(String[] args) {
        // 通过类初始化容器
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationCfg.class);
        MathService03 math = ctx.getBean("mathService03", MathService03.class);
        int n1 = 100, n2 = 5, n3 = 0;
        math.add(n1, n2);
        math.subtract(n1, n2);
        math.mutiply(n1, n2);
        try {
            math.divide(n1, n3);
        } catch (Exception e) {
            System.out.println("异常信息：" + e.getMessage());
        }

        UserService user = ctx.getBean("userService", UserService.class);
        user.show();
    }

}
