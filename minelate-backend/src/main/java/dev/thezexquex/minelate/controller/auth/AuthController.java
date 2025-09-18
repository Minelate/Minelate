package dev.thezexquex.minelate.controller.auth;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/auth")
public class AuthController {

    @Post("/login")
    public HttpResponse<Void> login() {
        return HttpResponse.ok();
    }
}
