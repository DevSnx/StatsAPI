package de.snx.statsapi;

import de.snx.statsapi.manager.FileManager;
import de.snx.statsapi.manager.SimpleStatsManager;
import de.snx.statsapi.mysql.SQLManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class StatsAPI extends JavaPlugin {

    public static StatsAPI instance;
    public static SimpleStatsManager simpleStatsManager;
    public static SQLManager sqlManager;
    public static FileManager fileManager;

    @Override
    public void onEnable() {
        instance = this;
        fileManager = new FileManager();
        Bukkit.getServer().getConsoleSender().sendMessage("§7+----------------------------------------------------+");
        Bukkit.getServer().getConsoleSender().sendMessage("§7|                                                    §7|");
        Bukkit.getServer().getConsoleSender().sendMessage("§7|                §cSimpleStatsAPI by Snx               §7|");
        Bukkit.getServer().getConsoleSender().sendMessage("§7|                        §bV: §e" + getInstance().getDescription().getVersion() + "                      §7|");
        Bukkit.getServer().getConsoleSender().sendMessage("§7|                                                    §7|");
        if (loadSQL()) {
            simpleStatsManager = new SimpleStatsManager();
            Bukkit.getServer().getConsoleSender().sendMessage("§7|                §aErfolgreich geladen!            §7|");
        }else{
            Bukkit.getServer().getConsoleSender().sendMessage("§7|           §cFehler! §7Keine Datenbank Verbindung!    §7|");
        }
        Bukkit.getServer().getConsoleSender().sendMessage("§7|                                                    §7|");
        Bukkit.getServer().getConsoleSender().sendMessage("§7+----------------------------------------------------+");
    }

    private boolean loadSQL() {
        FileConfiguration cfg = fileManager.getDatabaseFile().getConfig();
        String host = cfg.getString("DATABASE.HOST");
        String port = cfg.getString("DATABASE.PORT");
        String user = cfg.getString("DATABASE.USER");
        String pass = cfg.getString("DATABASE.PASSWORD");
        String database = cfg.getString("DATABASE.DATABASE");
        sqlManager = new SQLManager(host, port, user, pass, database);
        return sqlManager.openConnection();
    }


    @Override
    public void onDisable() {
        instance = null;
    }

    public static StatsAPI getInstance() {
        return instance;
    }

    public static SQLManager getSQLManager() {
        return sqlManager;
    }
}