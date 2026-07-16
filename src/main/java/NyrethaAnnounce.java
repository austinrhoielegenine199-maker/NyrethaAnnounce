package me.nyrethaannounce;

import org.bukkit.plugin.java.JavaPlugin;

public class NyrethaAnnounce extends JavaPlugin {

    private static NyrethaAnnounce instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getCommand("announce").setExecutor(new AnnounceCommand());
        getLogger().info("§bNyrethaAnnounce enabled!");
    }

    public static NyrethaAnnounce getInstance() {
        return instance;
    }
}
