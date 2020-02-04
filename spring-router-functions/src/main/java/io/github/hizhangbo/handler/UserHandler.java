package io.github.hizhangbo.handler;

import io.github.hizhangbo.domain.User;
import io.github.hizhangbo.repo.UserRepository;
import io.github.hizhangbo.util.CheckUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author Bob
 * @date 2020-02-03 13:51
 */
@Component
public class UserHandler {

    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> getAllUser(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON)
                .body(userRepository.findAll(), User.class);
    }

    public Mono<ServerResponse> createUser(ServerRequest request) {
        Mono<User> user = request.bodyToMono(User.class);

        return user.flatMap(u -> {
            CheckUtil.checkName(u.getName());

            return ok().contentType(APPLICATION_JSON)
                    .body(userRepository.saveAll(user), User.class);

        });
    }

    public Mono<ServerResponse> deleteUserById(ServerRequest request) {
        String id = request.pathVariable("id");

        return userRepository.findById(id)
                .flatMap(userRepository::delete)
                .then(ok().build())
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    //    public Mono<ServerResponse> create(ServerRequest request) {
//        return request.bodyToMono(User.class)
//                .flatMap(this::validateCreate)
//                .switchIfEmpty(ServerResponse.badRequest().build());
//    }
//    private Mono<ServerResponse> validateCreate(User user) {
//        if (user.getDescription() == null
//                || user.getName() == null
//                || user.getPassword() == null
//                || user.getRole() == null
//                || user.getUsername() == null) {
//            return ServerResponse.badRequest().syncBody("User Invalid");
//        } else {
//            return ServerResponse.ok().body(repo.save(user), User.class);
//        }
//    }
}
