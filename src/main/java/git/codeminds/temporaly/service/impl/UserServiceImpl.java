package git.codeminds.temporaly.service.impl;

import git.codeminds.temporaly.dto.auth.SignUpRequest;
import git.codeminds.temporaly.entity.*;
import git.codeminds.temporaly.repository.RoleRepository;
import git.codeminds.temporaly.repository.UserRepository;
import git.codeminds.temporaly.service.AccountService;
import git.codeminds.temporaly.service.UserService;
import git.codeminds.temporaly.utils.security.service.IBearerTokenService;
import git.codeminds.temporaly.utils.security.service.IHashingService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/11/24 @ 19:48
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final IHashingService hashingService;
    private final IBearerTokenService tokenService;

    private final AccountService accountService;

    @Override
    public Optional<User> signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }

        var roles = request.roles() == null || request.roles().isEmpty() ? new ArrayList<Role>() : new ArrayList<>(request.roles()
                .stream()
                .map(r -> roleRepository.findByName(Roles.valueOf(r.toUpperCase())).orElseThrow(() -> new RuntimeException("Role not found")))
                .toList());

        if (roles.isEmpty()) {
            roles.add(roleRepository.findByName(Role.getDefaultRole().getName()).orElseThrow(() -> new RuntimeException("Role not found")));
        }

        var accountInfo = new AccountInfo();
        accountInfo.setNames(List.of(request.names().split(" ")));
        accountInfo.setLastNames(List.of(request.lastNames().split(" ")));

        Optional<Account> account = accountService.createAccount(accountInfo);
        if (account.isEmpty()) {
            throw new RuntimeException("Account not created");
        }
        var user = new User(
                request.email(),
                hashingService.encode(request.password()),
                Set.copyOf(roles),
                account.get()
        );

        userRepository.save(user);
        return userRepository.findByEmail(user.getEmail());
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    /**
     * Deleted user by id
     * Find user in id, delete account also
     * @param id {@link String}
     */
    @Override
    public void deleteById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        Account account = user.get().getAccount();
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        userRepository.deleteById(id);
        accountService.deleteAccount(account.getId());
    }

    /**
     * Generate JWT from User Account.
     * Its obligatory that user has an account.
     * @param usernameOrEmail as {@link String}
     * @param password as {@link String}
     * @return {@link Optional} of {@link ImmutablePair} of {@link User} and {@link String}
     */
    @Override
    public Optional<ImmutablePair<User, String>> signIn(String usernameOrEmail, String password) {
        var user = userRepository.findByEmailOrAccountUsername(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!hashingService.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        if (!Objects.nonNull(user.getAccount())) {
            throw new RuntimeException("Cannot create JWT, cuz account is null, try to create a new account");
        }
        var token = tokenService.generateToken(user.getAccount().getUsername());
        return Optional.of(new ImmutablePair<>(user, token));
    }

    @Override
    public Optional<ImmutablePair<User, String>> refreshToken(String refreshToken) {
        var username = tokenService.getUsernameFromToken(refreshToken);
        var user = userRepository.findByAccountUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        assert user.getAccount() != null;
        // Verify if user has an account
        var token = tokenService.generateToken(user.getAccount().getUsername());
        return Optional.of(new ImmutablePair<>(user, token));
    }
}