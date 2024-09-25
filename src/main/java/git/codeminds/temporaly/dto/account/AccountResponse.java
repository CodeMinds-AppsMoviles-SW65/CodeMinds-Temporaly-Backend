package git.codeminds.temporaly.dto.account;

import git.codeminds.temporaly.entity.Account;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/25/24 @ 18:42
 */
public record AccountResponse(
        String id,
        String username,
        List<String> names,
        List<String> lastNames
) {

    public static AccountResponse fromAccount(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getUsername(),
                account.getInfo().getNames(),
                account.getInfo().getLastNames()
        );
    }
}
