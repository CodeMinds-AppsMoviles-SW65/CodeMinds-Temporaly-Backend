package git.codeminds.temporaly.entity;

import lombok.Getter;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 10/18/24 @ 17:09
 */
public enum Subscription {

    FREE(TimeUnit.MINUTES.toMillis(10), false, 3),
    PRO(TimeUnit.MINUTES.toMillis(30), false, 10),
    MASTER(TimeUnit.MINUTES.toMillis(60), false, Integer.MAX_VALUE),

    ;

    private final long mailDuration;

    @Getter
    private final boolean canRecover;

    @Getter
    private final int maxHistory;

    Subscription(long mailDuration, boolean canRecover, int maxHistory) {
        this.mailDuration = mailDuration;
        this.canRecover = canRecover;
        this.maxHistory = maxHistory;
    }

    public Date getExpirationDate() {
        Date date = new Date();
        date.setTime(date.getTime() + mailDuration);
        return date;
    }
}
