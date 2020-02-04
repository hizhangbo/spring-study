package io.github.hizhangbo;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.TimeUnit;

/**
 * @author Bob
 * @date 2020-01-31 16:34
 * <p>
 * 阻塞场景
 */
@Log4j2
public class Example1 {

    public final void execute() {
        long startTime = System.currentTimeMillis();
        doLoad();
        long costTime = System.currentTimeMillis() - startTime;
        log.info("总耗时：{}毫秒", costTime);
    }

    protected void doLoad() {
        loadConfigurations();
        loadUsers();
        loadOrders();
    }

    protected final void loadConfigurations() {
        loadMock("loadConfigurations", 1);
    }

    protected final void loadUsers() {
        loadMock("loadUsers", 2);
    }

    protected final void loadOrders() {
        loadMock("loadOrders", 3);
    }

    private void loadMock(String source, int seconds) {
        long startTime = System.currentTimeMillis();
        long milliseconds = TimeUnit.SECONDS.toMillis(seconds);
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long costTime = System.currentTimeMillis() - startTime;
        log.info("[线程：{}] {} 耗时：{}毫秒", Thread.currentThread().getName(), source, costTime);
    }

    public static void main(String[] args) {
        new Example1().execute();
    }
}
