package io.github.hizhangbo.handler;

import io.github.hizhangbo.exception.CheckException;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * @author Bob
 * @date 2020-02-03 19:24
 */
@Component
@Order(-2)
public class ExceptionHandler implements WebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        ServerHttpResponse response = serverWebExchange.getResponse();

        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.getHeaders().setContentType(MediaType.TEXT_PLAIN);

        String errorMsg = toStr(throwable);
        DataBuffer dataBuffer = response.bufferFactory().wrap(errorMsg.getBytes());

        return response.writeWith(Mono.just(dataBuffer));
    }

    private String toStr(Throwable throwable) {

        if (throwable instanceof CheckException) {
            CheckException ex = (CheckException) throwable;
            return ex.getFieldName() + " :invalid value " + ex.getFieldValue();
        } else {
            throwable.printStackTrace();
            return throwable.toString();
        }
    }
}
