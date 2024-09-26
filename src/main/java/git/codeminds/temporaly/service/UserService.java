package git.codeminds.temporaly.service;

import git.codeminds.temporaly.dto.auth.SignUpRequest;
import git.codeminds.temporaly.entity.User;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/11/24 @ 19:44
 */
public interface UserService {

    Optional<User> signUp(SignUpRequest request);

    Optional<User> findById(String id);

    void deleteById(String id);

    void changePassword(String id, String newPassword);

    Optional<ImmutablePair<User, String>> signIn(String usernameOrEmail, String password);

    Optional<ImmutablePair<User, String>> refreshToken(String refreshToken);
}
