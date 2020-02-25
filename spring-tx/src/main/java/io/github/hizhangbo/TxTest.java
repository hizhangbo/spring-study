package io.github.hizhangbo;

import io.github.hizhangbo.dao.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Bob
 * @date 2020-02-25 1:33
 */
@ComponentScan("io.github.hizhangbo")
public class TxTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxTest.class);
//        applicationContext.getEnvironment().setActiveProfiles("dev");
        UserDao userDao = applicationContext.getBean(UserDao.class);

        userDao.insert();
        System.out.println("insert user");
        applicationContext.close();
    }
}
