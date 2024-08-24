package git.codeminds.temporaly.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporaly-backend
 * Date: 23/08/24 @ 23:34
 */
@Configuration
public class OpenApiConfiguration {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Bean
    public OpenAPI learningPlatformOpenApi() {
        // General configuration
        var openApi = new OpenAPI();
        openApi.info(new Info()
                .title("Temporally API")
                .description("API for Temporally project")
                .version("v1.0.0")
                .license(new License().name("Apache 2.0")
                        .url("https://springdoc.org")));

        boolean isRunningInProd = activeProfile.equals("prod") || activeProfile.equals("dev");

        if (isRunningInProd) {
            openApi.servers(Collections.singletonList(new Server().url("https://temporally.ryzeon.me")));
        } else {
            openApi.servers(Collections.singletonList(new Server().url("http://localhost:8080")));
        }

        final String securitySchemeName = "bearerAuth";

        openApi.addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
        return openApi;
    }
}