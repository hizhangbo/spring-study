package io.github.hizhangbo.project.controller;

import io.github.hizhangbo.project.domain.User;
import io.github.hizhangbo.project.repo.UserRepository;
import io.github.hizhangbo.project.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author Bob
 * @date 2020-02-02 12:02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public Flux<User> getAll(){
        return userRepository.findAll();
    }

    @GetMapping(value = "/stream/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll(){
        return userRepository.findAll();
    }

    @PostMapping("/")
    public Mono<User> createUser(@Valid @RequestBody User user){
        CheckUtil.checkName(user.getName());
        user.setId(null);
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") String id){
        return userRepository.findById(id)
                .flatMap(user -> this.userRepository.delete(user)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable("id") String id,@Valid @RequestBody User user){
        return userRepository.findById(id)
                .flatMap(u->{
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    return userRepository.save(u);
                })
                .map(u-> new ResponseEntity<>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> findUserById(@PathVariable("id") String id){
        return userRepository.findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/age/{start}/{end}")
    public Flux<User> findByAge(@PathVariable("start") int start, @PathVariable("end") int end){
        return userRepository.findByAgeBetween(start, end);
    }

    @GetMapping(value = "/stream/age/{start}/{end}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindByAge(@PathVariable("start") int start, @PathVariable("end") int end){
        return userRepository.findByAgeBetween(start, end);
    }
}
