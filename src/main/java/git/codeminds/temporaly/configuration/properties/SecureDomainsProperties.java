package git.codeminds.temporaly.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 25/10/24 @ 00:31
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "secure")
public class SecureDomainsProperties {

    private List<String> domains;

}