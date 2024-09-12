package git.codeminds.temporaly.service.impl;

import git.codeminds.temporaly.entity.User;
import git.codeminds.temporaly.service.OauthService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/11/24 @ 19:57
 */
@Service(value = "oauthGoogleService")
public class OauthGoogleService implements OauthService {
    @Override
    public Optional<User> signUp(String token) {
        return Optional.empty();
    }

    @Override
    public Optional<ImmutablePair<User, String>> signIn(String token) {
        return Optional.empty();
    }
}
