package dev.thezexquex.minelate.controller.auth;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.net.URI;
import java.security.Principal;

@Controller("/auth")
@Secured(SecurityRule.IS_ANONYMOUS)
public class AuthController {

    @Post("/login")
    public HttpResponse<Void> login() {
        var location = URI.create("oauth/login/authentik");
        return HttpResponse.redirect(location);
    }

    @Post("/logout")
    public HttpResponse<Void> logout() {
        return HttpResponse.ok();
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/me")
    public Principal me(Principal principal) {
        return principal;
    }
}
