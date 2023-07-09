# Playtime logger plugin
This plugin logs the playtime of players and sends them to external MongoDB database.

## Installation
1. Download the plugin from releases page
2. Put the plugin into the `plugins` folder of your server
3. Start the server

## Configuration
The configuration file is located at `plugins/PlaytimeLogger/config.yml`.
```yaml
# MongoDB connection string
MONGODB_CONNECTION_STRING: String
# MongoDB database name
MONGODB_DATABASE: String
# MongoDB collection name
MONGODB_COLLECTION: String
# Interval to save the playtime to the database (in minutes)
SAVE_INTERVAL: int
```

## Commands
| Command            | Permission              | Description                                                  |
|--------------------|-------------------------|--------------------------------------------------------------|
| `/save-playtime`   | `playtimelogger.save`   | Saves playtimes to database, works the same as periodic task |
| `/reload-playtime` | `playtimelogger.reload` | Reloads plugin config                                        |

## Permissions
| Permission            | Description                  |
|-----------------------|------------------------------|
| `playtimelogger.save` | Allows to use `/save-playtime`|
| `playtimelogger.reload` | Allows to use `/reload-playtime`|
 | `playtimelogger.*` | Allows to use all plugin commands|
