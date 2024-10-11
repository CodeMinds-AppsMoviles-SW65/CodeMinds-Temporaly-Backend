package git.codeminds.temporaly.pojo;

import lombok.Data;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 11/10/24 @ 02:14
 */
@Data
public class EmailMessageContentAttachment {

    private String filename;

    private String contentType;

    private int size;

    public EmailMessageContentAttachment(String filename, String contentType, int size) {
        this.filename = filename;
        this.contentType = contentType;
        this.size = size;
    }

    public EmailMessageContentAttachment() {
    }
}
