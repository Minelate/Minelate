package dev.thezexquex.minelate.paper.plugin;

import dev.thezexquex.minelate.api.service.LocaleServiceIml;
import dev.thezexquex.minelate.api.Minelate;
import dev.thezexquex.minelate.api.Translation;
import dev.thezexquex.minelate.api.TranslationProvider;
import dev.thezexquex.minelate.api.service.LocaleService;
import dev.thezexquex.minelate.api.service.MinelateAPI;
import dev.thezexquex.minelate.paper.api.provider.PaperLocaleProvider;
import dev.thezexquex.minelate.paper.plugin.command.LanguageCommand;
import dev.thezexquex.minelate.paper.plugin.command.MinelateCommand;
import dev.thezexquex.minelate.paper.plugin.listener.PlayerJoinQuitListener;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.LegacyPaperCommandManager;
import org.incendo.cloud.paper.PaperCommandManager;

import java.net.URI;
import java.util.Locale;

public class MinelatePaperPlugin extends JavaPlugin implements Listener {
    private static Translation<Player> translation;
    private LocaleService localeService;
    private TranslationProvider<Player> translationProvider;

    @Override
    public void onEnable() {

        this.localeService = new LocaleServiceIml(URI.create("http://localhost:8080/api/"), "asd", Locale.ENGLISH);
        var localeProvider = new PaperLocaleProvider(localeService);
        this.translationProvider = new TranslationProvider<>(getDataFolder().toPath(), localeProvider);
        MinelateAPI<Player> minelateAPI = new MinelateAPI<>(
                translationProvider
        );

        Minelate.setInstance(minelateAPI);

        LegacyPaperCommandManager<CommandSender> commandManager = LegacyPaperCommandManager.createNative(
                this, ExecutionCoordinator.simpleCoordinator()
        );

        new LanguageCommand(this).apply(commandManager);
        new MinelateCommand(this).apply(commandManager);

        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinQuitListener(localeService), this);

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

    public LocaleService localeService() {
        return localeService;
    }

    public void reload() {
        translationProvider.reloadTranslations();
    }
}
