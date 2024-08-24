package git.codeminds.temporaly.utils.security.builder;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 23/08/24 @ 23:45
 */
public class UsernamePasswordAuthenticationTokenBuilder {

    // Prevent instantiation
    private UsernamePasswordAuthenticationTokenBuilder() {
    }

    public static UsernamePasswordAuthenticationToken build(UserDetails principal, HttpServletRequest request) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(principal,
                null,
                principal.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }

}