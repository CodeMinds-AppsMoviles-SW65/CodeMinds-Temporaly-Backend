package git.codeminds.temporaly.service;

import git.codeminds.temporaly.dto.auth.SignUpRequest;
import git.codeminds.temporaly.entity.User;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/11/24 @ 19:56
 */
public interface OauthService {

    Optional<User> signUp(String token);

    Optional<ImmutablePair<User, String>> signIn(String token);

}
