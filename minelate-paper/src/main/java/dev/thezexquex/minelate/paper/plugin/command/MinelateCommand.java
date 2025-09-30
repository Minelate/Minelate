package dev.thezexquex.minelate.paper.plugin.command;

import dev.thezexquex.minelate.paper.plugin.MinelatePaperPlugin;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.context.CommandContext;

import java.util.Locale;

public class MinelateCommand extends BaseCommand {
    public MinelateCommand(MinelatePaperPlugin plugin) {
        super(plugin);
    }

    @Override
    public void apply(CommandManager<CommandSender> commandManager) {
        commandManager.command(commandManager.commandBuilder("minelate")
                .permission("minelate.command.minelate")
                .senderType(CommandSender.class)
                .literal("reload")
                .handler(this::handleReload)
        );
    }

    private void handleReload(@NonNull CommandContext<CommandSender> context) {
        plugin.reload();
        context.sender().sendMessage(MinelatePaperPlugin.translation().component(Locale.ENGLISH, "command.minelate.reload"));
    }
}
