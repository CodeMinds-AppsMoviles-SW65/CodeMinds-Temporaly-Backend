package git.codeminds.temporaly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 11/10/24 @ 02:09
 */
@Document
@Data
public class TempMail {

    @Id
    private String id;

    private String username;

    private String domain;

    private Date expirationDate;

    private boolean canRecover;

    public TempMail(String username, String domain, Subscription subscription) {
        this.username = username;
        this.domain = domain;
        this.expirationDate = subscription.getExpirationDate();
        this.canRecover = subscription.isCanRecover();
    }

    public TempMail() {
    }

    @JsonIgnore
    public String getEmail() {
        return username + "@" + domain;
    }

}
