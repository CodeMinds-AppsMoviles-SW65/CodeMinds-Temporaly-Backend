package git.codeminds.temporaly.dto.auth;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/11/24 @ 19:45
 */
public record SignInRequest(
        String usernameOrEmail,
        String password
) {
}