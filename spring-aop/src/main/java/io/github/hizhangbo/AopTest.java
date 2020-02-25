package io.github.hizhangbo;

import io.github.hizhangbo.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.stream.Stream;

/**
 * @author Bob
 * @date 2020-02-24 11:50
 */
@EnableAspectJAutoProxy
@Configuration
@ComponentScan("io.github.hizhangbo")
public class AopTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopTest.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.sayHi();

//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        Stream.of(beanDefinitionNames).forEach(System.out::println);

        applicationContext.close();
    }
}
