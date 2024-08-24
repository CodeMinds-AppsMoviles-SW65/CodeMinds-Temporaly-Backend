package git.codeminds.temporaly.repository;

import git.codeminds.temporaly.entity.Role;
import git.codeminds.temporaly.entity.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 23/08/24 @ 23:52
 */
@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    boolean existsByName(Roles name);

    Optional<Role> findByName(Roles name);
}