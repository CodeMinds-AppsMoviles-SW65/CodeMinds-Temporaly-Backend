package git.codeminds.temporaly.controller;

import git.codeminds.temporaly.dto.account.AccountResponse;
import git.codeminds.temporaly.dto.account.UpdateAccountRequest;
import git.codeminds.temporaly.entity.Account;
import git.codeminds.temporaly.service.AccountService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/25/24 @ 18:36
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/account", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Account", description = "Account Endpoints")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Account found",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AccountResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account not found"
            )

    })
    @GetMapping("/{username}")
    public ResponseEntity<AccountResponse> findAccount(@PathVariable String username) {
        Optional<Account> account = accountService.findByUsername(username);
        return account.map(value -> ResponseEntity.ok(AccountResponse.fromAccount(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable String username, @RequestBody UpdateAccountRequest request) {
        Optional<Account> account = accountService.findByUsername(username);
        if (account.isPresent()) {
            Account dbAccount = account.get();
            accountService.updateAccount(dbAccount.getId(), request);
            return ResponseEntity.ok(AccountResponse.fromAccount(accountService.findById(dbAccount.getId()).get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/activate/{username}/{code}")
    public ResponseEntity<String> activateAccount(@PathVariable String username, @PathVariable String code) {
        // code logic
        return ResponseEntity.ok("Account activated successfully");
    }
}
