package git.codeminds.temporaly.repository;

import git.codeminds.temporaly.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 23/08/24 @ 23:52
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByAccountUsername(String username);

    Optional<User> findByEmailOrAccountUsername(String email, String username);

    boolean existsByEmail(String email);
}