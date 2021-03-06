package io.github.hizhangbo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author Bob
 * @since 2021/2/11 9:53
 * {@link SpringApplication#callRunners(ApplicationContext, ApplicationArguments)}
 */
@SpringBootApplication
public class RunnerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunnerApplication.class, args);
    }
}
