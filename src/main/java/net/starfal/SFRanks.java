package net.starfal;

import net.starfal.Database.Database;
import net.starfal.Utilities.Color;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SFRanks extends JavaPlugin {
    private String pluginPrefix = "<gradient:#85edff:#00ffb7>SFRanks</gradient>";
    public String getPluginPrefix() {
        return pluginPrefix;
    }

    private static SFRanks instance;
    public static SFRanks getInstance() {
        return instance;
    }

    private Database database;

    @Override
    public void onEnable() {
        instance = this;
        log(pluginPrefix + " <dark_gray>| <yellow>Enabling...");
        log(pluginPrefix + " <dark_gray>| <yellow>Trying to connect to database... <gray>(SQLite)");
        try{
            if (!getDataFolder().exists()){
                getDataFolder().mkdirs();
            }
            database = new Database(getDataFolder().getAbsolutePath() + "/data.db");
            log(pluginPrefix + " <dark_gray>| <green>Database Connection was successful!");
        } catch (Exception e){
            log(pluginPrefix + " <dark_gray>| <red>Failed to close the connection to the database! " + e.getMessage());
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        log(pluginPrefix + " <dark_gray>| <green>Enabled!");
    }

    @Override
    public void onDisable() {
        log(pluginPrefix + " <dark_gray>| <yellow>Disabling...");
        log(pluginPrefix + " <dark_gray>| <yellow>Trying to close the database connection...");
        try{
            database.closeConnection();
            log(pluginPrefix + " <dark_gray>| <green>Database Connection was closed successfully!");
        } catch (Exception e){
            log(pluginPrefix + " <dark_gray>| <red>Failed to close the connection to the database! " + e.getMessage());
            e.printStackTrace();
        }
        log(pluginPrefix + " <dark_gray>| <red>Disabled!");
    }
    public void log(String message) {
        Bukkit.getConsoleSender().sendMessage(Color.format(message));
    }

}
