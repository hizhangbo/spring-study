package io.github.hizhangbo;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Bob
 * @date 2020-01-31 16:50
 * <p>
 * 并行场景
 */
public class Example2 extends Example1 {

    @Override
    protected void doLoad() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService completionService = new ExecutorCompletionService(executorService);
        completionService.submit(super::loadConfigurations, null);
        completionService.submit(super::loadUsers, null);
        completionService.submit(super::loadOrders, null);

        int count = 0;
        while (count < 3) {
            if (completionService.poll() != null) {
                count++;
            }
        }
        executorService.shutdown();
    }

    public static void main(String[] args) {
        new Example2().execute();
    }
}
