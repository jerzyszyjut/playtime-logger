package com.koszykplugins.playtimelogger.runnables;

import com.koszykplugins.playtimelogger.PlaytimeLogger;
import org.bukkit.scheduler.BukkitRunnable;

public class SavePlaytimeRunnable extends BukkitRunnable {
      private final PlaytimeLogger playtimeLogger;
      public SavePlaytimeRunnable(PlaytimeLogger playtimeLogger) {
            this.playtimeLogger = playtimeLogger;
      }
      @Override
      public void run() {
            playtimeLogger.savePlaytime();
      }
}
