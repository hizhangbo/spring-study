package io.github.hizhangbo.proxy;

/**
 * 动态代理
 * @author Bob
 * @date 2020-02-03 22:13
 */
public interface IProxyCreator {
    Object createProxy(Class<?> type);
}
