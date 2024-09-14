package git.codeminds.temporaly.dto.auth;

import git.codeminds.temporaly.entity.User;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/11/24 @ 19:47
 */
public record SignUpResponse(
        String id,
        String username,
        String email,
        List<String> roles
) {

    public static SignUpResponse fromUser(User user) {
        return new SignUpResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles().stream().map(role -> role.getName().name()).toList()
        );
    }
}