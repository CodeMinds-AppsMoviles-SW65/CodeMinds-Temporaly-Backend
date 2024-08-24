package git.codeminds.temporaly.utils.security.builder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import git.codeminds.temporaly.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 23/08/24 @ 23:46
 */
@Getter
@EqualsAndHashCode
public class UserDetailsBuilder implements UserDetails {

    private final String username;
    @JsonIgnore
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsBuilder(String username, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public static UserDetailsBuilder build(User user) {
        var authorities = user.getRoles().stream().map(role -> role.getName().name())
                .map(SimpleGrantedAuthority::new).toList();
        return new UserDetailsBuilder(user.getUsername(), user.getPassword(), authorities);
    }
}