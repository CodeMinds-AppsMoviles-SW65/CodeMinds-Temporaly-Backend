package git.codeminds.temporaly.service.impl;

import git.codeminds.temporaly.entity.Account;
import git.codeminds.temporaly.entity.AccountInfo;
import git.codeminds.temporaly.repository.AccountRepository;
import git.codeminds.temporaly.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/25/24 @ 17:59
 */
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Optional<Account> createAccount(AccountInfo accountInfo) {
        Account account = new Account(accountInfo);
        accountRepository.save(account);
        return findByUsername(account.getUsername());
    }

    @Override
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}
