package io.github.hizhangbo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import io.github.hizhangbo.domain.User;
import org.springframework.core.env.Environment;

/**
 * @author Bob
 * @since 2020/12/6 14:48
 *
 * 这个测试说明：
 * 1. Spring IoC 的容器是 ApplicationContext 通过组合 DefaultListableBeanFactory 代理实现， BeanFactory 接口作为容器的顶层定义
 * 2. ApplicationContext 是 BeanFactory 的子接口， beanFactory.getBean() 使用时通过组合了一个 DefaultListableBeanFactory 对象来实现
 * 3. 组合的 DefaultListableBeanFactory 对象通过容器启动时，调用 refresh()方法创建
 * 4. ObjectFactory 延迟加载？
 *
 * ApplicationContext是BeanFactory的子接口，说明ApplicationContext is BeanFactory，
 * 并且ApplicationContext 是BeanFactory的包装类，也就是内部组合了BeanFactory的实现 DefaultListableBeanFactory，
 *
 * 继承 BeanFactory 就是为了 方便用个 getBean 这些方法，也就是代理模式了，实际实现通过代理类 DefaultListableBeanFactory。
 * 为什么包装了DefaultListableBeanFactory，因为它需要简化且丰富功能来为企业开发提供更高的便捷性，
 * 也就是说ApplicationContext 是DefaultListableBeanFactory的超集。
 */
public class IocTest {
    public static void main(String[] args) {

        // 刷新spring容器，底层调用refresh()
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/init-user-context.xml");

//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/init-user-context.xml");
//        applicationContext.refresh();

        User user = beanFactory.getBean(User.class);
        System.out.println(user);

        System.out.println(user.getBeanFactory());
        System.out.println(beanFactory);
        System.out.println(user.getBeanFactory() == beanFactory);
        // ObjectFactory 不会产生新的对象

        System.out.println(user.getObjectFactory().getObject());
        System.out.println(user.getObjectFactory().getObject() == beanFactory);

        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);
    }
}
