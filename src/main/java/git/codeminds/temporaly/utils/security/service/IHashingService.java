package git.codeminds.temporaly.utils.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 23/08/24 @ 23:41
 */
public interface IHashingService extends PasswordEncoder {

    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);
}