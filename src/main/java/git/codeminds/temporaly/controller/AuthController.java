package git.codeminds.temporaly.controller;

import git.codeminds.temporaly.dto.auth.SignInRequest;
import git.codeminds.temporaly.dto.auth.SignInResponse;
import git.codeminds.temporaly.dto.auth.SignUpRequest;
import git.codeminds.temporaly.dto.auth.SignUpResponse;
import git.codeminds.temporaly.entity.MessageResponse;
import git.codeminds.temporaly.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/11/24 @ 19:42
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sign in successfully",
                    content = @Content(schema = @Schema(implementation = SignInResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid credentials",
                    content = @Content(schema = @Schema(implementation = MessageResponse.class))
            )
    })
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest) {
        var details = userService.signIn(signInRequest.usernameOrEmail(), signInRequest.password()).orElseThrow(() -> new RuntimeException("An error occurred while signing in"));
        var signInResponse = SignInResponse.fromDetails(details);
        return new ResponseEntity<>(signInResponse, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Sign up successfully",
                    content = @Content(schema = @Schema(implementation = SignUpResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid credentials",
                    content = @Content(schema = @Schema(implementation = MessageResponse.class))
            )
    })
    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        var user = userService.signUp(signUpRequest).orElseThrow(() -> new RuntimeException("An error occurred while signing up"));
        var signUpResponse = SignUpResponse.fromUser(user);
        return new ResponseEntity<>(signUpResponse, HttpStatus.CREATED);
    }

    @PostMapping("/refresh-token/{refreshToken}")
    public ResponseEntity<SignInResponse> refreshToken(@PathVariable String refreshToken) {
        var details = userService.refreshToken(refreshToken).orElseThrow(() -> new RuntimeException("An error occurred while refreshing token"));
        var signInResponse = SignInResponse.fromDetails(details);
        return new ResponseEntity<>(signInResponse, HttpStatus.OK);
    }
}