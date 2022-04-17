package io.github.hizhangbo.embedded;

import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bob
 * @since 2022/4/12 20:54
 */
@Configuration
public class MyWebServerFactoryCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers(connector -> {
            connector.setPort(9090);
            Http11NioProtocol http11NioProtocol = (Http11NioProtocol) connector.getProtocolHandler();
            http11NioProtocol.setMinSpareThreads(20);
            http11NioProtocol.setMaxThreads(100);
            http11NioProtocol.setAcceptCount(100);
            http11NioProtocol.setKeepAliveTimeout(10000);
            http11NioProtocol.setMaxKeepAliveRequests(1000);

            // http11NioProtocol
        });


        // factory.add
    }
}
