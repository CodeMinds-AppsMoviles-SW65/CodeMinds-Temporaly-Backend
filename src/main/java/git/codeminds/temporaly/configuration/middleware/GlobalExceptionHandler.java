package git.codeminds.temporaly.configuration.middleware;

import git.codeminds.temporaly.entity.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.io.IOException;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 24/08/24 @ 00:00
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Value("${spring.profiles.active}")
    private String activeProfile;

    private void logError(Exception e) {
        if (activeProfile.equals("dev")) {
            logger.error("Debugging error", e);
        }
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<MessageResponse> handleRuntimeException(RuntimeException e) {
        logError(e);
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }

    @ExceptionHandler({IOException.class})
    public ResponseEntity<MessageResponse> handleIOException(IOException e) {
        logError(e);
        return ResponseEntity.badRequest().body(new MessageResponse(e.getLocalizedMessage()));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<MessageResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        logError(e);
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }

    @ExceptionHandler({MissingServletRequestPartException.class})
    public ResponseEntity<MessageResponse> handleMissingServletRequestPartException(MissingServletRequestPartException e) {
        logError(e);
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }

}