package dev.thezexquex.minelate.velocity.plugin;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.thezexquex.minelate.api.service.LocaleServiceIml;
import dev.thezexquex.minelate.api.Minelate;
import dev.thezexquex.minelate.api.Translation;
import dev.thezexquex.minelate.api.TranslationProvider;
import dev.thezexquex.minelate.api.service.LocaleService;
import dev.thezexquex.minelate.api.service.MinelateAPI;
import dev.thezexquex.minelate.velocity.api.provider.VelocityLocaleProvider;
import dev.thezexquex.minelate.velocity.plugin.command.MinelateCommand;
import org.incendo.cloud.SenderMapper;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.velocity.VelocityCommandManager;

import java.net.URI;
import java.nio.file.Path;
import java.util.Locale;
import java.util.logging.Logger;

public class MinelateVelocityPlugin {

    private final PluginContainer container;
    private final Path dataDirectory;
    private final ProxyServer server;
    private final Logger logger;
    private static Translation<Player> translation;
    private TranslationProvider<Player> translationProvider;

    @Inject
    public MinelateVelocityPlugin(PluginContainer container, ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.container = container;
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {

        VelocityCommandManager<CommandSource> commandManager = new  VelocityCommandManager(
                container,
                server,
                ExecutionCoordinator.simpleCoordinator(),
                SenderMapper.identity()
        );

        new MinelateCommand(this).apply(commandManager);

        LocaleService localeService = new LocaleServiceIml(URI.create("localhost:8080/api"), "asd", Locale.ENGLISH);
        var localeProvider = new VelocityLocaleProvider(localeService);
        this.translationProvider = new TranslationProvider<>(dataDirectory, localeProvider);
        MinelateAPI<Player> minelateAPI = new MinelateAPI<>(
                translationProvider
        );

        Minelate.setInstance(minelateAPI);

        // IN A PLUGIN THAT USES MINELATE:
        var provider = Minelate.<Player>getInstance().translationProvider();
        translation = provider.registerTranslationProject("minelate-velocity");
    }

    public static Translation<Player> translation() {
        return translation;
    }

    public void reload() {
        translationProvider.reloadTranslations();
    }
}
