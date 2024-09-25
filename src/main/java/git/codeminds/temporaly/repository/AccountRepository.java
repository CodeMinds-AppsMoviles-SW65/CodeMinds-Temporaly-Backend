package git.codeminds.temporaly.repository;

import git.codeminds.temporaly.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/25/24 @ 18:37
 */
@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    Optional<Account> findByUsername(String username);
}
