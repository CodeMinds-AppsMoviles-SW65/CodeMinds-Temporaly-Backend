package git.codeminds.temporaly.dto.auth;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/11/24 @ 19:46
 */
public record SignUpRequest(
        String names,
        String lastNames,
        String email,
        String password,
        List<String> roles
) {
}