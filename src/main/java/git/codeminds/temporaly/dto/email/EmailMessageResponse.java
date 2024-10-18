package git.codeminds.temporaly.dto.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 11/10/24 @ 02:35
 */
@Getter
@AllArgsConstructor
@ToString
public class EmailMessageResponse {

    private int id;

    private String from;

    private String to;

    private String subject;

    private String date;

    private List<EmailMessageAttachmentResponse> attachments;

    private String body;

    private String textBody;

    private String htmlBody;
}
