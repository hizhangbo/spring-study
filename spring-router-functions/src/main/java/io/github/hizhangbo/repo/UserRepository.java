package io.github.hizhangbo.repo;

import io.github.hizhangbo.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author Bob
 * @date 2020-02-02 12:02
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Flux<User> findByAgeBetween(int start, int end);

    @Query("'age':{'$gte':20,'$lte':24}")
    Flux<User> find();
}
