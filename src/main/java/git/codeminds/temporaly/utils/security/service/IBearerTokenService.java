package git.codeminds.temporaly.utils.security.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 23/08/24 @ 23:41
 */
public interface IBearerTokenService {

    String generateToken(String username);

    String getBearerTokenFrom(HttpServletRequest request);

    String generateToken(Authentication authentication);

    String getUsernameFromToken(String token);

    boolean validateToken(String token);
}