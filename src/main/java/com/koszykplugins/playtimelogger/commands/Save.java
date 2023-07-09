package com.koszykplugins.playtimelogger.commands;

import com.koszykplugins.playtimelogger.PlaytimeLogger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Save implements CommandExecutor {
    private final PlaytimeLogger playtimeLogger;

    public Save(PlaytimeLogger playtimeLogger) {
        this.playtimeLogger = playtimeLogger;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        playtimeLogger.savePlaytime();
        return true;
    }
}
