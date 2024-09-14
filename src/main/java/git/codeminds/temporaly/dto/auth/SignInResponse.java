package git.codeminds.temporaly.dto.auth;

import git.codeminds.temporaly.entity.User;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/11/24 @ 19:46
 */
public record SignInResponse(
        String id,
        String username,
        String email,
        String token
) {

    public static SignInResponse fromDetails(
            ImmutablePair<User, String> details) {
        return new SignInResponse(
                details.getLeft().getId(),
                details.getLeft().getUsername(),
                details.getLeft().getEmail(),
                details.getRight()
        );
    }
}