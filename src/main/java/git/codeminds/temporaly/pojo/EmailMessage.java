package git.codeminds.temporaly.pojo;

import lombok.Data;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 11/10/24 @ 02:14
 */
@Data
public class EmailMessage {

    private int id;

    private String from;

    private String subject;

    private String date;

    public EmailMessage(int id, String from, String subject, String date) {
        this.id = id;
        this.from = from;
        this.subject = subject;
        this.date = date;
    }

    public EmailMessage() {
    }
}
