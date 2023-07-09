package com.koszykplugins.playtimelogger.commands;

import com.koszykplugins.playtimelogger.PlaytimeLogger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SavePlaytime implements CommandExecutor {
    private final PlaytimeLogger playtimeLogger;

    public SavePlaytime(PlaytimeLogger playtimeLogger) {
        this.playtimeLogger = playtimeLogger;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(playtimeLogger.savePlaytime()) {
            sender.sendMessage("Saved playtime");
            playtimeLogger.getLogger().info("Saved playtime, command executed by " + sender.getName() + "");
            return true;
        } else {
            sender.sendMessage("Failed to save playtime");
            playtimeLogger.getLogger().warning("Failed to save playtime, command executed by " + sender.getName() + "");
            return false;
        }
    }
}
