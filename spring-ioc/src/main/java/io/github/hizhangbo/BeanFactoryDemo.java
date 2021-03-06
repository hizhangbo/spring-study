package io.github.hizhangbo;

import io.github.hizhangbo.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author Bob
 * @since 2020/12/6 15:30
 *
 * 这个例子展示不通过 ApplicationContext 直接使用 IoC 容器进行依赖注入。
 */
public class BeanFactoryDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        int beanDefinitionsCount = reader.loadBeanDefinitions("classpath:/META-INF/init-user-context.xml");
        System.out.println(beanDefinitionsCount);

        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User userByObjectFactory = objectFactory.getObject();
        System.out.println("userByObjectFactory.getBeanFactory = " + userByObjectFactory.getBeanFactory());

        User user = beanFactory.getBean(User.class);
        System.out.println(userByObjectFactory == user);

    }
}
