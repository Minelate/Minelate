package dev.thezexquex.minelate.paper.plugin.command;

import dev.thezexquex.minelate.paper.plugin.MinelatePaperPlugin;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.CommandManager;

public abstract class BaseCommand {
    protected final MinelatePaperPlugin plugin;

    public BaseCommand(MinelatePaperPlugin plugin) {
        this.plugin = plugin;
    }

    public abstract void apply(CommandManager<CommandSender> commandManager);
}
