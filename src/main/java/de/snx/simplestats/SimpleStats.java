package de.snx.simplestats;

import de.snx.simplestats.api.SimpleStatsAPI;
import de.snx.simplestats.manager.SimpleStatsManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleStats extends JavaPlugin {

    public static SimpleStats instance;
    public static SimpleStatsManager simpleStatsManager;

    @Override
    public void onEnable() {
        instance = this;
        simpleStatsManager = new SimpleStatsManager();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static SimpleStats getInstance() {
        return instance;
    }
}