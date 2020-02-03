package de.snx.statsapi.file;

import org.bukkit.configuration.file.FileConfiguration;

public class DatabaseFile extends FileBase{

    public DatabaseFile() {
        super("", "databse");
        writeDefaults();
    }

    private void writeDefaults() {
        FileConfiguration cfg = getConfig();

        cfg.addDefault("DATABASE.HOST", "localhost");
        cfg.addDefault("DATABASE.PORT", "3306");
        cfg.addDefault("DATABASE.USER", "root");
        cfg.addDefault("DATABASE.PASSWORD", "password");
        cfg.addDefault("DATABASE.DATABASE", "database");

        cfg.options().copyDefaults(true);
        saveConfig();
    }
}