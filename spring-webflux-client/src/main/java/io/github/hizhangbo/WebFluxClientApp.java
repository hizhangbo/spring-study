package io.github.hizhangbo;

import io.github.hizhangbo.proxy.IProxyCreator;
import io.github.hizhangbo.proxy.impl.JDKProxyCreator;
import io.github.hizhangbo.remote.IUserApi;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Bob
 * @date 2020-02-03 22:09
 */
@SpringBootApplication
public class WebFluxClientApp {
    public static void main(String[] args) {
        SpringApplication.run(WebFluxClientApp.class, args);
    }

    @Bean
    IProxyCreator jdkProxyCreator() {
        return new JDKProxyCreator();
    }

    @Bean
    FactoryBean<IUserApi> userApi(IProxyCreator proxyCreator) {
        return new FactoryBean<IUserApi>() {
            @Override
            public IUserApi getObject() throws Exception {
                return (IUserApi) proxyCreator.createProxy(this.getObjectType());
            }

            @Override
            public Class<?> getObjectType() {
                return IUserApi.class;
            }
        };
    }
}
