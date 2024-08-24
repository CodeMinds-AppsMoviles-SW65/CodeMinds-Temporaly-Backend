package git.codeminds.temporaly.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 23/08/24 @ 23:49
 */
@Document
public class Role {

    @Getter
    @Id
    private String id;

    @Getter
    private Roles name;

    public Role(Roles name) {
        this.name = name;
    }

    public Role() {}

    public static Role toRoleFromName(String name) {
        return new Role(Roles.valueOf(name));
    }

    public static Role getDefaultRole() {
        return new Role(Roles.ROLE_USER);
    }
}