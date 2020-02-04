package io.github.hizhangbo.router;

import io.github.hizhangbo.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author Bob
 * @date 2020-02-03 14:17
 */
@Configuration
public class AllRouters {

    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler handler) {
        return RouterFunctions.nest(
                path("user"),
                route(GET("/"), handler::getAllUser)
                        .andRoute(POST("/").and(accept(APPLICATION_JSON)), handler::createUser)
                        .andRoute(DELETE("/{id}"), handler::deleteUserById)
        );
    }
}
