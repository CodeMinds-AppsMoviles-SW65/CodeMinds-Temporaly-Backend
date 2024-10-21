package git.codeminds.temporaly.service;

import git.codeminds.temporaly.dto.account.UpdateAccountRequest;
import git.codeminds.temporaly.entity.Account;
import git.codeminds.temporaly.entity.AccountInfo;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/25/24 @ 17:59
 */
public interface AccountService {

    Optional<Account> createAccount(AccountInfo accountInfo, String username);

    void deleteAccount(String id);

    void updateAccount(String id, UpdateAccountRequest request);

    void save(Account account);

    Optional<Account> findByUsername(String username);

    Optional<Account> findById(String id);
}
