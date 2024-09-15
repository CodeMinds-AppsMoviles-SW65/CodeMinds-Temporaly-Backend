package git.codeminds.temporaly.service.impl;

import git.codeminds.temporaly.dto.auth.SignUpRequest;
import git.codeminds.temporaly.entity.Role;
import git.codeminds.temporaly.entity.Roles;
import git.codeminds.temporaly.entity.User;
import git.codeminds.temporaly.entity.UserInfo;
import git.codeminds.temporaly.repository.RoleRepository;
import git.codeminds.temporaly.repository.UserRepository;
import git.codeminds.temporaly.service.UserService;
import git.codeminds.temporaly.utils.security.service.IBearerTokenService;
import git.codeminds.temporaly.utils.security.service.IHashingService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

        var userInfo = new UserInfo();
        userInfo.setNames(List.of(request.names().split(" ")));
        userInfo.setLastNames(List.of(request.lastNames().split(" ")));

        var user = new User(
                request.email(),
                hashingService.encode(request.password()),
                Set.copyOf(roles),
                userInfo
        );

        userRepository.save(user);
        return userRepository.findByUsername(user.getUsername());
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<ImmutablePair<User, String>> signIn(String usernameOrEmail, String password) {
        var user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!hashingService.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        var token = tokenService.generateToken(user.getUsername());
        return Optional.of(new ImmutablePair<>(user, token));
    }

    @Override
    public Optional<ImmutablePair<User, String>> refreshToken(String refreshToken) {
        var username = tokenService.getUsernameFromToken(refreshToken);
        var user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        var token = tokenService.generateToken(user.getUsername());
        return Optional.of(new ImmutablePair<>(user, token));
    }

//    @Override
//    public Optional<User> updateDetails(String id, UpdateUserDetailsRequest request) {
//        var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
//        if (userRepository.existsByUsername(request.username())) {
//            throw new RuntimeException("Cannot details, username already exists");
//        }
//        user.updateUserInfo(request);
//        userRepository.save(user);
//        return Optional.of(user);
//    }
}