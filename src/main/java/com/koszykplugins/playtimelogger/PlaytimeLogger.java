package com.koszykplugins.playtimelogger;

import com.koszykplugins.playtimelogger.commands.Reload;
import com.koszykplugins.playtimelogger.commands.SavePlaytime;
import com.koszykplugins.playtimelogger.runnables.SavePlaytimeRunnable;
import com.mongodb.client.MongoCollection;
import io.papermc.lib.PaperLib;
import org.bukkit.Statistic;
import org.bukkit.plugin.java.JavaPlugin;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.Date;

public class PlaytimeLogger extends JavaPlugin {
  @Override
  public void onEnable() {
    PaperLib.suggestPaper(this);

    registerSavePlaytimeRunnable();
    registerCommands();
    saveDefaultConfig();
  }

  private void registerCommands() {
    getCommand("save-playtime").setExecutor(new SavePlaytime(this));
    getCommand("reload-playtime").setExecutor(new Reload(this));
  }

  private void registerSavePlaytimeRunnable() {
    long saveInterval = getConfig().getLong("SAVE_INTERVAL");

    SavePlaytimeRunnable savePlaytimeRunnable = new SavePlaytimeRunnable(this);
    savePlaytimeRunnable.runTaskTimer(this, 0, saveInterval * 20 * 60);
  }

  public boolean savePlaytime() {
    try {
      MongoCollection<Document> collection = getCollection();
      Date timestamp = new java.util.Date();

      Document document = new Document("timestamp", timestamp);

      Arrays.stream(getServer().getOfflinePlayers()).forEach(player -> {
        document.append(player.getName(), player.getStatistic(Statistic.PLAY_ONE_MINUTE));
      });

      collection.insertOne(document);
      return true;
    } catch (Exception e) {
      getLogger().warning("Failed to save playtime");
      return false;
    }
  }

  private MongoCollection<Document> getCollection() {
    String connectionString = getConfig().getString("MONGODB_CONNECTION_STRING");
    String databaseName = getConfig().getString("MONGODB_DATABASE_NAME");
    String collectionName = getConfig().getString("MONGODB_COLLECTION_NAME");

    if (connectionString == null || databaseName == null || collectionName == null
            || connectionString.isEmpty() || databaseName.isEmpty() || collectionName.isEmpty()) {
      throw new MongoException("Config is not set correctly, update config.yml");
    }

    ServerApi serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build();
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(connectionString))
            .serverApi(serverApi)
            .build();

    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase(databaseName);

    return database.getCollection(collectionName);
  }
}
