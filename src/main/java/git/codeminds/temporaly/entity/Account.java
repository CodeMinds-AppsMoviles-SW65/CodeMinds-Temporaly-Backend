package git.codeminds.temporaly.entity;

import git.codeminds.temporaly.utils.UserUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

import java.util.Date;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/25/24 @ 17:54
 */
@Document
@Getter
public class Account {

    @Id
    private String id;

    private boolean active;

    @Setter
    private String username;

    @Unwrapped(onEmpty = Unwrapped.OnEmpty.USE_NULL, prefix = "info_")
    private AccountInfo info;

    @CreatedDate
    private Date createdDate;

    public Account() {
    }

    public Account(AccountInfo info) {
        this.active = false;
        this.info = info;
        this.username =  "";
    }

    public String getFullName() {
        return String.join(" ", this.info.getNames()) + " " + String.join(" ", this.info.getLastNames());
    }

    public boolean isActivated() {
        return this.active;
    }

    public void activateAccount() {
        this.active = true;
    }
}
