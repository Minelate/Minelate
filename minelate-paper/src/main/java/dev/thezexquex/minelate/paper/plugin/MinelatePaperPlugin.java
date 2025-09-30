package dev.thezexquex.minelate.paper.plugin;

import dev.thezexquex.minelate.api.LocaleServiceIml;
import dev.thezexquex.minelate.api.Minelate;
import dev.thezexquex.minelate.api.Translation;
import dev.thezexquex.minelate.api.TranslationProvider;
import dev.thezexquex.minelate.api.service.LocaleService;
import dev.thezexquex.minelate.api.service.MinelateAPI;
import dev.thezexquex.minelate.paper.api.provider.PaperLocaleProvider;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URI;
import java.util.Locale;

public class MinelatePaperPlugin extends JavaPlugin implements Listener {
    private static Translation<Player> translation;


    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        LocaleService localeService = new LocaleServiceIml(URI.create("localhost:8080/api"), "asd", Locale.ENGLISH);
        var localeProvider = new PaperLocaleProvider(localeService);
        TranslationProvider<Player> translationProvider = new TranslationProvider<>(getDataFolder().toPath(), localeProvider);
        MinelateAPI<Player> minelateAPI = new MinelateAPI<>(
                translationProvider
        );

        Minelate.setInstance(minelateAPI);

        // IN A PLUGIN THAT USES MINELATE:
        var provider = Minelate.<Player>getInstance().translationProvider();
        translation = provider.registerTranslationProject("minelate-paper");
    }

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        var player = event.getPlayer();
        player.sendMessage(translation().component(player, "test.message", Placeholder.unparsed("player", player.getName())));
    }

    @Override
    public void onDisable() {

    }

    public static Translation<Player> translation() {
        return translation;
    }
}
