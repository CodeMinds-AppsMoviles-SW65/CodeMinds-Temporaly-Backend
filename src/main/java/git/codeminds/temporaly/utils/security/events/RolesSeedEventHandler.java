package git.codeminds.temporaly.utils.security.events;

import git.codeminds.temporaly.entity.Role;
import git.codeminds.temporaly.entity.Roles;
import git.codeminds.temporaly.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 23/08/24 @ 23:53
 */
@Service
@AllArgsConstructor
public class RolesSeedEventHandler {

    private final RoleRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(RolesSeedEventHandler.class);

    private void seedRoles() {
        logger.info("Seeding roles...");
        Arrays.stream(Roles.values())
                .forEach(role -> {
                    if (!repository.existsByName(role)) {
                        repository.save(new Role(role));
                        logger.info("Role {} seeded", role);
                    }
                }
                );
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var appName = event.getApplicationContext().getId();
        logger.info("Starting seed and permissions setup for {}...", appName);
        seedRoles();
    }
}
