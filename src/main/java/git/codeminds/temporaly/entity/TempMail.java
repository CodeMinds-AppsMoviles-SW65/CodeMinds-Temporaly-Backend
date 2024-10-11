package git.codeminds.temporaly.entity;

import lombok.Data;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 11/10/24 @ 02:09
 */
@Data
public class TempMail {

    private String username;

    private String domain;

    public TempMail(String username, String domain) {
        this.username = username;
        this.domain = domain;
    }

    public TempMail() {
    }

    public String getEmail() {
        return username + "@" + domain;
    }

}
