package io.github.hizhangbo.proxy.impl;

import io.github.hizhangbo.anno.ApiServer;
import io.github.hizhangbo.bean.MethodInfo;
import io.github.hizhangbo.bean.ServerInfo;
import io.github.hizhangbo.proxy.IProxyCreator;
import io.github.hizhangbo.proxy.IRestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Bob
 * @date 2020-02-03 22:15
 */
@Slf4j
public class JDKProxyCreator implements IProxyCreator {
    @Override
    public Object createProxy(Class<?> type) {
        log.info("createProxy:" + type);
        ServerInfo serverInfo = extractServerInfo(type);

        log.info("server info:" + serverInfo);
        IRestHandler IRestHandler = new WebClientRestHandler();

        IRestHandler.init(serverInfo);
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{type}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        MethodInfo methodInfo = extractMethodInfo(method, args);
                        log.info("method info:" + methodInfo);
                        return IRestHandler.invokeRest(methodInfo);
                    }
                });
    }

    private MethodInfo extractMethodInfo(Method method, Object[] args) {
        MethodInfo methodInfo = new MethodInfo();

        // 获取REST方法类型
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof GetMapping) {
                GetMapping anno = (GetMapping) annotation;

                methodInfo.setUrl(anno.value()[0]);
                methodInfo.setMethod(HttpMethod.GET);
            } else if (annotation instanceof PostMapping) {
                PostMapping anno = (PostMapping) annotation;

                methodInfo.setUrl(anno.value()[0]);
                methodInfo.setMethod(HttpMethod.POST);
            } else if (annotation instanceof DeleteMapping) {
                DeleteMapping anno = (DeleteMapping) annotation;

                methodInfo.setUrl(anno.value()[0]);
                methodInfo.setMethod(HttpMethod.DELETE);
            } else if (annotation instanceof PutMapping) {
                PutMapping anno = (PutMapping) annotation;

                methodInfo.setUrl(anno.value()[0]);
                methodInfo.setMethod(HttpMethod.PUT);
            }
        }

        // 获取方法参数
        Parameter[] parameters = method.getParameters();
        Map<String, Object> params = new LinkedHashMap<>();

        for (int i = 0; i < parameters.length; i++) {
            PathVariable annoPath = parameters[i].getAnnotation(PathVariable.class);

            if (annoPath != null) {
                params.put(annoPath.value(), args[i]);
            }

            // 获取请求体
            RequestBody annoBody = parameters[i].getAnnotation(RequestBody.class);

            if (annoBody != null) {
                methodInfo.setBody((Mono<?>) args[i]);
            }
        }

        // 获取返回类型 flux or mono
        boolean isFlux = method.getReturnType().isAssignableFrom(Flux.class);
        methodInfo.setReturnFlux(isFlux);

        // 获取返回值类型
        Type genericReturnType = method.getGenericReturnType();
        Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
        Class<?> elementType = (Class<?>) actualTypeArguments[0];
        methodInfo.setReturnElementType(elementType);


        return methodInfo;
    }

    private ServerInfo extractServerInfo(Class<?> type) {
        ServerInfo serverInfo = new ServerInfo();

        ApiServer anno = type.getAnnotation(ApiServer.class);
        serverInfo.setUrl(anno.value());
        return serverInfo;
    }
}
