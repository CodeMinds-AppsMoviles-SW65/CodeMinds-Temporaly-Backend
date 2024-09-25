package git.codeminds.temporaly.service;

import git.codeminds.temporaly.entity.Account;
import git.codeminds.temporaly.entity.AccountInfo;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/25/24 @ 17:59
 */
public interface AccountService {

    Optional<Account> createAccount(AccountInfo accountInfo);

    void deleteAccount(String id);

    Optional<Account> findByUsername(String username);
}
