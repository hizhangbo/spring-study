package io.github.hizhangbo.controller;

import io.github.hizhangbo.domain.User;
import io.github.hizhangbo.remote.IUserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author Bob
 * @date 2020-02-03 21:57
 */
@RestController
public class UserController {

    @Autowired
    IUserApi userApi;

    @GetMapping("/")
    public void test() {
        Flux<User> users = userApi.getAll();
        users.subscribe(System.out::println);


        String id = "5e36e657c39d9878f0903824";
        userApi.findUserById(id).subscribe(user -> {
            System.out.println("findUserById:" + user);
        });


        userApi.deleteUser(id).subscribe(user -> {
            System.out.println("deleteUser");
        });
    }
}
