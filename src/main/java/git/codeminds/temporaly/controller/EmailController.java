package git.codeminds.temporaly.controller;

import git.codeminds.temporaly.dto.email.EmailMessageResponse;
import git.codeminds.temporaly.dto.email.OnceSecMail;
import git.codeminds.temporaly.entity.TempMail;
import git.codeminds.temporaly.entity.User;
import git.codeminds.temporaly.repository.TempMailRepository;
import git.codeminds.temporaly.service.OneSecEmailService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 11/10/24 @ 02:46
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/email", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Temporary Email", description = "Temporary Email Endpoints")
@AllArgsConstructor
public class EmailController {

    private final OneSecEmailService oneSecEmailService;
    private final TempMailRepository tempMailRepository;

    @ApiResponse(
            description = "Generate a temporary email",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = TempMail.class))
    )
    @GetMapping("/generate")
    public HttpEntity<OnceSecMail> generateTempMail() {
        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        if (username == null) {
            throw new IllegalArgumentException("Invalid user");
        }
//        TempMail tempMail = oneSecEmailService.createTempMail();
//        return new HttpEntity<>(tempMail);
    }

    @ApiResponse(
            description = "Retrieve all email messages for a temporary email",
            responseCode = "200",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = EmailMessageResponse.class)))
    )
    @PostMapping("/messages")
    public HttpEntity<List<EmailMessageResponse>> getAllMessage(@RequestBody OnceSecMail tempMail) {
        if (!tempMailRepository.existsByUsernameAndDomain(tempMail.username(), tempMail.domain())) {
            throw new IllegalArgumentException("Invalid temporary email");
        }
        TempMail mail = tempMailRepository.findByUsernameAndDomain(tempMail.username(), tempMail.domain());
        List<EmailMessageResponse> emailMessages = oneSecEmailService.getAllMessages(mail);
        return new HttpEntity<>(emailMessages);
    }
}
