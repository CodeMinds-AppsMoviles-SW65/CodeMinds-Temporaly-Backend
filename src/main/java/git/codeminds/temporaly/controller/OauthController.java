package git.codeminds.temporaly.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: temporally-backend
 * Date: 9/11/24 @ 19:41
 */
@RestController
@Hidden
@RequestMapping("/api/v1/authentication/oauth")
public class OauthController {


    // Endpoint de éxito de autenticación
    @GetMapping("/success")
    @ResponseBody
    public String oauthLoginSuccess(@AuthenticationPrincipal OAuth2User principal) {
        // Puedes acceder a la información del usuario autenticado desde el objeto `principal`
        String email = principal.getAttribute("email");
        String name = principal.getAttribute("name");

        // Aquí puedes manejar los detalles del usuario, guardarlos en la base de datos, generar JWT, etc.
        return "Autenticación exitosa. Usuario: " + name + " - Email: " + email;
    }

    // Endpoint de fallo de autenticación
    @GetMapping("/failure")
    @ResponseBody
    public String oauthLoginFailure() {
        return "Error en la autenticación con OAuth2.";
    }

    // Opcional: Endpoint para logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";  // Redirige a la página de inicio o a donde desees
    }

    // Opcional: Endpoint para obtener información del usuario autenticado
    @GetMapping("/user")
    @ResponseBody
    public Principal user(Principal principal) {
        return principal;  // Devuelve los detalles del usuario autenticado
    }
}
