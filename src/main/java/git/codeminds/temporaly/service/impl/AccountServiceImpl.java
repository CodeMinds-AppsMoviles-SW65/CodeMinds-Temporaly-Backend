package git.codeminds.temporaly.service.impl;

import git.codeminds.temporaly.dto.account.UpdateAccountRequest;
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
    public Optional<Account> createAccount(AccountInfo accountInfo, String username) {
        Account account = new Account(accountInfo);
        account.setUsername(username);
        accountRepository.save(account);
        return findByUsername(account.getUsername());
    }

    @Override
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void updateAccount(String id, UpdateAccountRequest request) {
        accountRepository.findById(id).ifPresent(account -> {
            account.setUsername(request.username());
            accountRepository.save(account);
        });
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Optional<Account> findById(String id) {
        return Optional.empty();
    }
}
