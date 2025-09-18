package dev.thezexquex.minelate.paper;

import dev.thezexquex.minelate.api.Translation;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;

import java.util.Locale;

public class PaperTranslation extends Translation<Player> {

    private PaperTranslation(String projectId) {
        super(projectId);
    }

    @Override
    public Component component(Player player, String key, TagResolver... tagResolvers) {
        return null;
    }

    @Override
    public Component component(Locale locale, String key, TagResolver... tagResolvers) {
        return null;
    }

    @Override
    protected String applyPapiPlaceholders(String translationString, Player player) {
        return PlaceholderAPI.setPlaceholders(player, translationString);
    }
}
