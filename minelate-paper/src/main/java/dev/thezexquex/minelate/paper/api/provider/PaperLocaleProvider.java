package dev.thezexquex.minelate.paper.api.provider;

import dev.thezexquex.minelate.api.LocaleProvider;
import dev.thezexquex.minelate.api.service.LocaleService;
import org.bukkit.entity.Player;

import java.util.Locale;

public class PaperLocaleProvider implements LocaleProvider<Player> {
    private final LocaleService localeService;

    public PaperLocaleProvider(LocaleService localeService) {
        this.localeService = localeService;
    }

    @Override
    public Locale getPlayerLocale(Player player) {
        return localeService.getLocale(player.getUniqueId());
    }
}
