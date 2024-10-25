package git.codeminds.temporaly.service;

import git.codeminds.temporaly.dto.email.EmailMessageResponse;
import git.codeminds.temporaly.dto.email.OnceSecMail;
import git.codeminds.temporaly.dto.email.OnceSecMailInfo;
import git.codeminds.temporaly.entity.TempMail;
import git.codeminds.temporaly.pojo.EmailMessage;
import git.codeminds.temporaly.pojo.EmailMessageContent;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 11/10/24 @ 01:52
 */
public interface OneSecEmailService {

    List<String> getDomains();

    Optional<OnceSecMail> createTempMail(String username);

    List<EmailMessage> getEmails(TempMail tempMail);

    EmailMessageContent getEmailContent(TempMail tempMail, EmailMessage emailMessage);

    List<EmailMessageResponse> getAllMessages(TempMail tempMail);

    List<OnceSecMailInfo> getEmailHistory(String username);
}
