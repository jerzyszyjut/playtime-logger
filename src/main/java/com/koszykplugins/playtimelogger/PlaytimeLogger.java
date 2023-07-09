package com.koszykplugins.playtimelogger;

import com.koszykplugins.playtimelogger.runnables.SavePlaytime;
import io.papermc.lib.PaperLib;
import org.bukkit.plugin.java.JavaPlugin;
import org.mongodb


/**
 * Created by Levi Muniz on 7/29/20.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class PlaytimeLogger extends JavaPlugin {
  @Override
  public void onEnable() {
    PaperLib.suggestPaper(this);

    this.registerSavePlaytimeRunnable();

    saveDefaultConfig();
  }

  private void registerSavePlaytimeRunnable() {
    long saveInterval = getConfig().getLong("SAVE_INTERVAL");

    SavePlaytime savePlaytime = new SavePlaytime();
    savePlaytime.runTaskTimer(this, 0, saveInterval * 20 * 60 * 60);
  }

  public void savePlaytime() {
    MongoClientURI uri = new MongoClientURI("mongodb://" + username + ":" + password + "@" + host + ":" + port + "/" + database + "?authSource=admin");
    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
    MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
  }
}
