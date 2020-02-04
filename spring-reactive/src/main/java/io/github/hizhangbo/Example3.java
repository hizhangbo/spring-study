package io.github.hizhangbo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Bob
 * @date 2020-01-31 22:03
 * <p>
 * Future.get 阻塞
 */
public class Example3 extends Example1 {
    @Override
    protected void doLoad() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        runCompletely(executorService.submit(super::loadConfigurations));
        runCompletely(executorService.submit(super::loadUsers));
        runCompletely(executorService.submit(super::loadOrders));
    }

    private void runCompletely(Future<?> future) {
        try {
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Example3().execute();
    }
}
