package git.codeminds.temporaly.entity;

import git.codeminds.temporaly.utils.UserUtils;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

import java.util.Set;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 23/08/24 @ 23:46
 */
@Getter
@Document
public class User {

    @Id
    private String id;

    private String username;

    private String email;

    private String password;

    @DBRef
    private Set<Role> roles;

    @Unwrapped(onEmpty = Unwrapped.OnEmpty.USE_NULL, prefix = "info_")
    private UserInfo info;

    public User() {
    }

    public User(String email, String password, Set<Role> roles, UserInfo info) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.info = info;
        this.username = UserUtils.generateRandomUserName(info.getNames(), info.getLastNames());
    }

    public String getFullName() {
        return String.join(" ", this.info.getNames()) + " " + String.join(" ", this.info.getLastNames());
    }
}