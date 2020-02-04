package io.github.hizhangbo.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @author Bob
 * @date 2020-02-01 0:26
 */
public class FluxDemo {

    public static void main(String[] args) throws InterruptedException {
        Flux.just("A","B","C")
                .publishOn(Schedulers.elastic())
                .subscribe(FluxDemo::println);

        TimeUnit.SECONDS.sleep(1);
    }

    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("[线程：" + threadName + "] " + object);
    }
}
