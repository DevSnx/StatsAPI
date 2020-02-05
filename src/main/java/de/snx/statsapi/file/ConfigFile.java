package de.snx.statsapi.file;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigFile extends  FileBase{

    public ConfigFile(){
        super("", "config");
        writeDefaults();
    }

    private void writeDefaults() {
        FileConfiguration cfg = getConfig();
        cfg.addDefault("CONFIG.COMMAND.STATS", true);
        cfg.options().copyDefaults(true);
        saveConfig();
    }

    public boolean getBoolean(){
        return getConfig().getBoolean("CONFIG.COMMAND.STATS");
    }
}