package git.codeminds.temporaly.controller;

import git.codeminds.temporaly.dto.auth.SignInResponse;
import git.codeminds.temporaly.dto.auth.SignUpResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/11/24 @ 19:41
 */
@RestController
@Hidden
@RequestMapping("/api/v1/auth/google")
public class GoogleOauthController {

    @PostMapping
    public ResponseEntity<SignInResponse> signIn(@RequestBody Map<String, String> tokenData) {
        String googleToken = tokenData.get("token");
        Google
    }

    @PostMapping
    public ResponseEntity<SignUpResponse> signUp(@RequestBody Map<String, String> tokenData) {

    }
}

