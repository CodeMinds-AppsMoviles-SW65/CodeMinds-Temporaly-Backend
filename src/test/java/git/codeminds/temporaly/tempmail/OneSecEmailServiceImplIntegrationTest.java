package git.codeminds.temporaly.tempmail;

import git.codeminds.temporaly.entity.TempMail;
import git.codeminds.temporaly.service.OneSecEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 11/10/24 @ 01:57
 */
@SpringBootTest
class OneSecEmailServiceImplIntegrationTest {

    @Autowired
    private OneSecEmailService oneSecEmailService;

    @BeforeEach
    public void setUp() {
        // No need to initialize RestTemplate as it will be autowired by Spring
    }

    @Test
    void testGetDomains() {
        List<String> domains = oneSecEmailService.getDomains();
        assertNotNull(domains);
        domains.forEach(System.out::println);
    }
}