package dev.thezexquex.minelate.velocity.plugin.command;

import com.velocitypowered.api.command.CommandSource;
import dev.thezexquex.minelate.velocity.plugin.MinelateVelocityPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.context.CommandContext;

import java.util.Locale;

public class MinelateCommand extends BaseCommand {
    public MinelateCommand(MinelateVelocityPlugin plugin) {
        super(plugin);
    }

    @Override
    public void apply(CommandManager<CommandSource> commandManager) {
        commandManager.command(commandManager.commandBuilder("minelate")
                .permission("minelate.command.minelate")
                .literal("reload")
                .handler(this::handleReload)
        );
    }

    private void handleReload(@NonNull CommandContext<CommandSource> context) {
        plugin.reload();
        context.sender().sendMessage(MinelateVelocityPlugin.translation().component(Locale.ENGLISH, "command.minelate.reload"));
    }
}
