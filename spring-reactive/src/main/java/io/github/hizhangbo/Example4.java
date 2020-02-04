package io.github.hizhangbo;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.CompletableFuture;

/**
 * @author Bob
 * @date 2020-01-31 22:10
 *
 * CompletableFuture 实现非阻塞 执行链
 */
@Log4j2
public class Example4 extends Example1 {

    @Override
    protected void doLoad() {
        CompletableFuture.runAsync(super::loadConfigurations)
                .thenRun(super::loadUsers)
                .thenRun(super::loadOrders)
                .whenComplete((result, throwable) -> {
                    log.info("[线程：{}] 加载完成", Thread.currentThread().getName());
                })
                .join();
    }

    public static void main(String[] args) {
        new Example4().execute();
    }
}
