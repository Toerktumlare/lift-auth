package se.andolf.lift.auth.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.andolf.lift.auth.entities.UserEntity;

import java.util.Optional;

/**
 * @author Thomas on 2017-11-05.
 */
public interface UserRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByUserName(String username);
}
