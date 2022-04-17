package io.github.hizhangbo.ext;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bob
 * @since 2022/4/16 23:50
 */
// @Order(1)
public class Ext0ApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        Map<String, Object> map = new HashMap<>();
        map.put("ext0", "Ext0ApplicationContextInitializer");

        MapPropertySource mapPropertySource = new MapPropertySource("Ext0ApplicationContextInitializer", map);
        environment.getPropertySources().addLast(mapPropertySource);
        System.out.println("Ext0ApplicationContextInitializer...");
    }
}
