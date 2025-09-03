package de.unknowncity.minelate.paper.provider;

import de.unknowncity.minelate.api.LocaleProvider;
import org.bukkit.entity.Player;

import java.util.Locale;

public class PaperLocaleProvider implements LocaleProvider<Player> {


    @Override
    public Locale getPlayerLocale(Player player) {
        return null;
    }
}
