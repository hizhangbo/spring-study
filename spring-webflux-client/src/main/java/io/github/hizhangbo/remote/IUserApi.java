package io.github.hizhangbo.remote;

import io.github.hizhangbo.anno.ApiServer;
import io.github.hizhangbo.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author Bob
 * @date 2020-02-03 19:39
 */
@ApiServer("http://localhost:8080/user")
public interface IUserApi {

    @GetMapping("/")
    public Flux<User> getAll();

    @GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll();

    @PostMapping("/")
    Mono<User> createUser(@Valid @RequestBody Mono<User> user);

    @DeleteMapping("/{id}")
    Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") String id);

    @PutMapping("/{id}")
    Mono<ResponseEntity<User>> updateUser(@PathVariable("id") String id, @Valid @RequestBody Mono<User> user);

    @GetMapping("/{id}")
    Mono<ResponseEntity<User>> findUserById(@PathVariable("id") String id);

    @GetMapping("/age/{start}/{end}")
    Flux<User> findByAge(@PathVariable("start") int start, @PathVariable("end") int end);

    @GetMapping(value = "/stream/age/{start}/{end}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<User> streamFindByAge(@PathVariable("start") int start, @PathVariable("end") int end);
}
