package io.github.hizhangbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Bob
 * @since 2022/4/16 23:47
 * SpringBoot 执行流程及扩展点
 * 1. 扩展系统初始化器 ApplicationContextInitializer
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
