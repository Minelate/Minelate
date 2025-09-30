package dev.thezexquex.minelate.paper.plugin.command;

import dev.thezexquex.minelate.paper.plugin.MinelatePaperPlugin;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.context.CommandContext;
import org.incendo.cloud.suggestion.Suggestion;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

import static org.incendo.cloud.parser.standard.StringParser.stringParser;

public class LanguageCommand extends BaseCommand {
    private final List<Locale> locales = List.of(Locale.ENGLISH, Locale.GERMAN);

    public LanguageCommand(MinelatePaperPlugin plugin) {
        super(plugin);
    }

    public void apply(CommandManager<CommandSender> commandManager) {
        commandManager.command(commandManager.commandBuilder("language")
                .permission("minelate.command.language")
                .senderType(Player.class)
                .optional("locale", stringParser(), (context, input) -> CompletableFuture.completedFuture(
                        locales.stream().map(Locale::toLanguageTag).map(Suggestion::suggestion).toList()
                )).handler(this::handleLocale)
        );
    }

    private void handleLocale(@NonNull CommandContext<Player> context) {
        var sender = context.sender();
        if (!context.contains("locale")) {
            var currentLocale = plugin.localeService().getLocale(sender.getUniqueId());
            sender.sendMessage(MinelatePaperPlugin.translation().component(sender, "command.language.current", Placeholder.unparsed("locale", currentLocale.toLanguageTag())));
            return;
        }

        var localeTag = context.<String>get("locale");
        var newLocale = Locale.forLanguageTag(localeTag);
        plugin.localeService().setLocale(sender.getUniqueId(), newLocale);
        sender.sendMessage(MinelatePaperPlugin.translation().component(sender, "command.language.changed", Placeholder.unparsed("locale", newLocale.toLanguageTag())));
    }
}
