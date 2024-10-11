package git.codeminds.temporaly.configuration.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 11/10/24 @ 01:53
 */
@Configuration
public class BeansConfiguration {

    /**
     * @summary This method is used to create a RestTemplate bean
     * @return RestTemplate
     */
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
