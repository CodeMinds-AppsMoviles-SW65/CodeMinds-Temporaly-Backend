package git.codeminds.temporaly;

import git.codeminds.temporaly.configuration.properties.SecureDomainsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecureDomainsProperties.class)
public class TemporallyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemporallyBackendApplication.class, args);
    }

}
