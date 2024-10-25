package git.codeminds.temporaly.dto.email;

import git.codeminds.temporaly.entity.TempMail;

import java.util.Date;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 10/18/24 @ 17:37
 */
public record OnceSecMailInfo(String username, String domain, Date expirationDate, boolean canRecover) {

    public OnceSecMailInfo fromTempMail(TempMail tempMail) {
        return new OnceSecMailInfo(tempMail.getUsername(), tempMail.getDomain(), tempMail.getExpirationDate(), tempMail.isCanRecover());
    }
}

