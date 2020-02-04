package io.github.hizhangbo.proxy;

import io.github.hizhangbo.bean.MethodInfo;
import io.github.hizhangbo.bean.ServerInfo;

/**
 * @author Bob
 * @date 2020-02-03 22:25
 */
public interface IRestHandler {
    void init(ServerInfo serverInfo);

    Object invokeRest(MethodInfo methodInfo);
}
