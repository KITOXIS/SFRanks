package net.starfal;

import net.starfal.Utilities.Color;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SFRanks extends JavaPlugin {

    @Override
    public void onEnable() {
        log("<gold>SFRanks <gray>| Enabled!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void log(String message) {
        Bukkit.getConsoleSender().sendMessage(Color.format(message));
    }

}
