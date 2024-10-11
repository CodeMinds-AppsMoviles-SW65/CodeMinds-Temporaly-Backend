package git.codeminds.temporaly.service.impl;

import git.codeminds.temporaly.dto.email.EmailMessageAttachmentResponse;
import git.codeminds.temporaly.dto.email.EmailMessageResponse;
import git.codeminds.temporaly.entity.TempMail;
import git.codeminds.temporaly.pojo.EmailMessage;
import git.codeminds.temporaly.pojo.EmailMessageContent;
import git.codeminds.temporaly.service.OneSecEmailService;
import git.codeminds.temporaly.utils.EmailUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 11/10/24 @ 01:52
 */
@Service
public class OneSecEmailServiceImpl implements OneSecEmailService {

    private final String URL = "https://www.1secmail.com/api/v1/";

    private final RestTemplate restTemplate;

    private final HttpHeaders headers;

    private final List<String> domains;

    public OneSecEmailServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.domains = new ArrayList<>();

        this.headers = new HttpHeaders();
        this.headers.setAccept(List.of(org.springframework.http.MediaType.APPLICATION_JSON));
    }

    @Override
    public List<String> getDomains() {
        if (!domains.isEmpty()) {
            return domains;
        }
        HttpEntity<String[]> entity = new HttpEntity<>(headers);
        String[] exchange = restTemplate.exchange(URL + "?action=getDomainList", HttpMethod.GET, entity, String[].class).getBody();
        assert exchange != null;
        return  List.of(exchange);
    }

    @Override
    public TempMail createTempMail() {
        String domain = getDomains().get(ThreadLocalRandom.current().nextInt(getDomains().size()));
        String username = EmailUtils.generateRandomEmailUsername();
        return new TempMail(username, domain);
    }

    @Override
    public List<EmailMessage> getEmails(TempMail tempMail) {
        HttpEntity<EmailMessage[]> entity = new HttpEntity<>(headers);
        EmailMessage[] exchange = restTemplate.exchange(URL + "?action=getMessages&login=" + tempMail.getUsername() + "&domain=" + tempMail.getDomain(), HttpMethod.GET, entity, EmailMessage[].class).getBody();
        assert exchange != null;
        return List.of(exchange);
    }

    @Override
    public EmailMessageContent getEmailContent(TempMail tempMail, EmailMessage emailMessage) {
        HttpEntity<EmailMessageContent> entity = new HttpEntity<>(headers);
        EmailMessageContent exchange = restTemplate.exchange(URL + "?action=readMessage&login=" + tempMail.getUsername() + "&domain=" + tempMail.getDomain() + "&id=" + emailMessage.getId(), HttpMethod.GET, entity, EmailMessageContent.class).getBody();
        assert exchange != null;
        return exchange;
    }

    @Override
    public List<EmailMessageResponse> getAllMessages(TempMail tempMail) {
        List<EmailMessage> emails = getEmails(tempMail);
        List<EmailMessageResponse> emailMessageResponses = new ArrayList<>();
        for (EmailMessage email : emails) {
            EmailMessageContent emailContent = getEmailContent(tempMail, email);
            emailMessageResponses.add(new EmailMessageResponse(
                    email.getId(),
                    email.getFrom(),
                    tempMail.getEmail(),
                    email.getSubject(),
                    email.getDate(),
                    emailContent.getAttachments().stream().map(
                            attachment -> new EmailMessageAttachmentResponse(
                                    attachment.getFilename(),
                                    attachment.getContentType(),
                                    attachment.getSize(),
                                    emailContent.getAttachmentDownloadUrl(tempMail, attachment)
                            )
                    ).toList(),
                    emailContent.getBody(),
                    emailContent.getTextBody(),
                    emailContent.getHtmlBody()));
        }
        return emailMessageResponses;
    }
}