# Configuración de Autenticación OAuth2 en Spring Boot

Este proyecto utiliza Spring Boot para proporcionar autenticación basada en OAuth2 con Google, GitHub y Facebook. A continuación, se detallan los pasos para configurar y usar la autenticación OAuth2 en tu aplicación.

## Requisitos

- **Java 17** o superior.
- **Spring Boot 3.x**.
- **Dependencias**: Asegúrate de tener las siguientes dependencias en tu archivo `pom.xml`.

```xml
<dependencies>
    <!-- Spring Boot Dependencies -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.5.0</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-oauth2-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-oauth2-jose</artifactId>
        <version>6.3.3</version>
    </dependency>
    <!-- Otros dependencies -->
</dependencies>
```
Configuración en `application.properties`


Configura las credenciales de OAuth2 para Google, GitHub y Facebook en tu archivo `application.properties`. Asegúrate de reemplazar los valores de `client-id`, `client-secret`, y `redirect-uri` con los valores correspondientes de tus aplicaciones en los paneles de desarrolladores de Google, GitHub y Facebook.
```properties
# OAuth2 Google Credentials
spring.security.oauth2.client.registration.google.client-id=${GOOGLE_CLIENT_ID}
spring.security.oauth2.client.registration.google.client-secret=${GOOGLE_CLIENT_SECRET}
spring.security.oauth2.client.registration.google.scope=profile,email
spring.security.oauth2.client.registration.google.redirect-uri={baseUrl}/login/oauth2/code/google

# OAuth2 GitHub Credentials
spring.security.oauth2.client.registration.github.client-id=${GITHUB_CLIENT_ID}
spring.security.oauth2.client.registration.github.client-secret=${GITHUB_CLIENT_SECRET}
spring.security.oauth2.client.registration.github.scope=read:user
spring.security.oauth2.client.registration.github.redirect-uri={baseUrl}/login/oauth2/code/github

# OAuth2 Facebook Credentials
spring.security.oauth2.client.registration.facebook.client-id=${FACEBOOK_CLIENT_ID}
spring.security.oauth2.client.registration.facebook.client-secret=${FACEBOOK_CLIENT_SECRET}
spring.security.oauth2.client.registration.facebook.scope=email,public_profile
spring.security.oauth2.client.registration.facebook.redirect-uri={baseUrl}/login/oauth2/code/facebook

# OAuth2 Provider URLs
spring.security.oauth2.client.provider.google.authorization-uri=https://accounts.google.com/o/oauth2/auth
spring.security.oauth2.client.provider.google.token-uri=https://accounts.google.com/o/oauth2/token
spring.security.oauth2.client.provider.google.user-info-uri=https://www.googleapis.com/oauth2/v3/userinfo

spring.security.oauth2.client.provider.github.authorization-uri=https://github.com/login/oauth/authorize
spring.security.oauth2.client.provider.github.token-uri=https://github.com/login/oauth/access_token
spring.security.oauth2.client.provider.github.user-info-uri=https://api.github.com/user

spring.security.oauth2.client.provider.facebook.authorization-uri=https://www.facebook.com/v12.0/dialog/oauth
spring.security.oauth2.client.provider.facebook.token-uri=https://graph.facebook.com/v12.0/oauth/access_token
spring.security.oauth2.client.provider.facebook.user-info-uri=https://graph.facebook.com/me?fields=id,name,email
```

## Endpoints
* Éxito de Inicio de Sesión: /api/v1/authentication/oauth2/success.
* Error de Inicio de Sesión: /api/v1/authentication/oauth2/failure.