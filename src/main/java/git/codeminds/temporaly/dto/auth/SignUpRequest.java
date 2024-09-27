package git.codeminds.temporaly.dto.auth;


/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/11/24 @ 19:46
 */
public record SignUpRequest(
        String username,
        String email,
        String password
) {
}