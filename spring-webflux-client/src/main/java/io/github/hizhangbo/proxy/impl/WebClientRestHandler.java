package io.github.hizhangbo.proxy.impl;

import io.github.hizhangbo.bean.MethodInfo;
import io.github.hizhangbo.bean.ServerInfo;
import io.github.hizhangbo.proxy.IRestHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Bob
 * @date 2020-02-03 22:51
 */
public class WebClientRestHandler implements IRestHandler {


    private WebClient clent;

    @Override
    public void init(ServerInfo serverInfo) {
        this.clent = WebClient.create(serverInfo.getUrl());
    }

    @Override
    public Object invokeRest(MethodInfo methodInfo) {
        Object result = null;

        WebClient.RequestBodyUriSpec requestBodyUriSpec = this.clent.method(methodInfo.getMethod());

        if (methodInfo.getParams() != null) {
            requestBodyUriSpec.uri(methodInfo.getUrl(), methodInfo.getParams());
        } else {
            requestBodyUriSpec.uri(methodInfo.getUrl());
        }

        WebClient.ResponseSpec retrieve = requestBodyUriSpec
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();


        if (methodInfo.isReturnFlux()) {
            result = retrieve.bodyToFlux(methodInfo.getReturnElementType());
        } else {
            result = retrieve.bodyToMono(methodInfo.getReturnElementType());
        }

        return result;
    }
}
