package git.codeminds.temporaly.repository;

import git.codeminds.temporaly.entity.TempMail;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 10/18/24 @ 17:34
 */
public interface TempMailRepository extends MongoRepository<TempMail, String> {

    boolean existsByUsernameAndDomain(String username, String domain);
}
