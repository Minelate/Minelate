package dev.thezexquex.minelate.controller.locale;

import dev.thezexquex.minelate.model.PlayerLocale;
import dev.thezexquex.minelate.repository.PlayerLocaleRepository;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.Optional;
import java.util.UUID;

@Controller("/api/locale")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class PlayerLocaleController {

    private final PlayerLocaleRepository playerLocaleRepository;

    public PlayerLocaleController(PlayerLocaleRepository repo) {
        this.playerLocaleRepository = repo;
    }

    @Get("/{uuid}")
    public Optional<PlayerLocale> get(UUID uuid) {
        return playerLocaleRepository.findByPlayerUuid(uuid);
    }

    @Get
    public Iterable<PlayerLocale> list() {
        return playerLocaleRepository.findAll();
    }

    @Post
    public void create(@Body PlayerLocale playerLocale) {
         playerLocaleRepository.upsert(playerLocale.playerUuid(), playerLocale.locale());
    }

    @Delete("/{uuid}")
    public void delete(UUID uuid) {
        playerLocaleRepository.deleteByPlayerUuid(uuid);
    }
}