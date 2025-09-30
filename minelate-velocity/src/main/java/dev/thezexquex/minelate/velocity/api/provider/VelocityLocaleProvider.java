package dev.thezexquex.minelate.velocity.api.provider;

import com.velocitypowered.api.proxy.Player;
import dev.thezexquex.minelate.api.LocaleProvider;
import dev.thezexquex.minelate.api.service.LocaleService;

import java.util.Locale;

public class VelocityLocaleProvider implements LocaleProvider<Player> {
    private final LocaleService localeService;

    public VelocityLocaleProvider(LocaleService localeService) {
        this.localeService = localeService;
    }

    @Override
    public Locale getPlayerLocale(Player player) {
        return localeService.getLocale(player.getUniqueId());
    }
}
