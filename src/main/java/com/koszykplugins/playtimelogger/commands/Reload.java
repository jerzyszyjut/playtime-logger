package com.koszykplugins.playtimelogger.commands;

import com.koszykplugins.playtimelogger.PlaytimeLogger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {
    private final PlaytimeLogger playtimeLogger;

    public Reload(PlaytimeLogger playtimeLogger) {
        this.playtimeLogger = playtimeLogger;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        playtimeLogger.reloadConfig();
        sender.sendMessage("Reloaded config");
        playtimeLogger.getLogger().info("Reloaded config, command executed by " + sender.getName() + "");
        return true;
    }
}
