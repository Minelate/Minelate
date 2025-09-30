package dev.thezexquex.minelate.velocity.plugin.command;

import com.velocitypowered.api.command.CommandSource;
import dev.thezexquex.minelate.velocity.plugin.MinelateVelocityPlugin;
import org.incendo.cloud.CommandManager;

public abstract class BaseCommand {
    protected final MinelateVelocityPlugin plugin;

    public BaseCommand(MinelateVelocityPlugin plugin) {
        this.plugin = plugin;
    }

    public abstract void apply(CommandManager<CommandSource> commandManager);
}
