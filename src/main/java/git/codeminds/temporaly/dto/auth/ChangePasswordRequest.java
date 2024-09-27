package git.codeminds.temporaly.dto.auth;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 26/09/24 @ 01:07
 */
public record ChangePasswordRequest(
        String accountMail,
        String newPassword
) {
}
