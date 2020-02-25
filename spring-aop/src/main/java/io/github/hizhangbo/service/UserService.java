package io.github.hizhangbo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Bob
 * @date 2020-02-24 11:52
 */
@Service
public class UserService {

    public void sayHi() {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Hi...");
    }
}
