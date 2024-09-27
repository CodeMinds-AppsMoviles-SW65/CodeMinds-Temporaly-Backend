package git.codeminds.temporaly.utils.security.service.impl;

import git.codeminds.temporaly.repository.UserRepository;
import git.codeminds.temporaly.utils.security.builder.UserDetailsBuilder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 23/08/24 @ 23:56
 */
@Service("defaultUserDetailsService")
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).stream().findFirst().orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserDetailsBuilder.build(user);
    }
}