package de.unknowncity.minelate;

import io.micronaut.http.annotation.*;

@Controller("/minelate-backend")
public class MinelateBackendController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}