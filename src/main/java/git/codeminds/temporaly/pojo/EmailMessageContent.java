package git.codeminds.temporaly.pojo;

import git.codeminds.temporaly.entity.TempMail;
import lombok.Data;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 11/10/24 @ 02:14
 */
@Data
public class EmailMessageContent {

    private int id;

    private String from;

    private String subject;

    private String date;

    private List<EmailMessageContentAttachment> attachments;

    private String body;

    private String textBody;

    private String htmlBody;

    public EmailMessageContent(int id, String from, String subject, String date, List<EmailMessageContentAttachment> attachments, String body, String textBody, String htmlBody) {
        this.id = id;
        this.from = from;
        this.subject = subject;
        this.date = date;
        this.attachments = attachments;
        this.body = body;
        this.textBody = textBody;
        this.htmlBody = htmlBody;
    }

    public EmailMessageContent() {
    }

    public String getAttachmentDownloadUrl(TempMail temp, EmailMessageContentAttachment attachment) {
        return "https://www.1secmail.com/api/v1/?action=download&login=" + temp.getUsername() + "&domain=" + temp.getDomain() + "&id=" + id + "&file=" + attachment.getFilename();
    }
}
