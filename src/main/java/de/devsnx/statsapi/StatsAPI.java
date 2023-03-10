package de.devsnx.statsapi;

import de.devsnx.statsapi.commands.CommandStats;
import de.devsnx.statsapi.events.PlayerEvents;
import de.devsnx.statsapi.manager.FileManager;
import de.devsnx.statsapi.manager.RankingManager;
import de.devsnx.statsapi.manager.StatsManager;
import de.devsnx.statsapi.mysql.SQLManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
public class StatsAPI extends JavaPlugin {
    public static StatsAPI instance;
    public static StatsManager statsManager;
    public static SQLManager sqlManager;
    public static FileManager fileManager;
    public static RankingManager rankingManager;
    @Override
    public void onEnable() {
        instance = this;
        fileManager = new FileManager();
        Bukkit.getServer().getConsoleSender().sendMessage("§7+----------------------------------------------------+");
        Bukkit.getServer().getConsoleSender().sendMessage("§7|                                                    §7|");
        Bukkit.getServer().getConsoleSender().sendMessage("§7|         §cStatsAPI by DevSnx and AJAR3TR0            §7|");
        Bukkit.getServer().getConsoleSender().sendMessage("§7|                        §bV: §e" + getInstance().getDescription().getVersion() + "                    §7|");
        Bukkit.getServer().getConsoleSender().sendMessage("§7|                                                    §7|");
        if (loadSQL()) {
            statsManager = new StatsManager();
            rankingManager = new RankingManager();

            if(getFileManager().getConfigFile().getStats() == true){
                getCommand("stats").setExecutor(new CommandStats());
            }
            Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);

            Bukkit.getServer().getConsoleSender().sendMessage("§7|                   §aLoading success!                 §7|");
        }else{
            Bukkit.getServer().getConsoleSender().sendMessage("§7|           §cNo Database Connection!          §7|");
        }
        Bukkit.getServer().getConsoleSender().sendMessage("§7|                                                    §7|");
        Bukkit.getServer().getConsoleSender().sendMessage("§7+----------------------------------------------------+");
    }

    @Override
    public void onDisable() {
        instance = null;
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

    public static StatsAPI getInstance() {
        return instance;
    }

    public static SQLManager getSQLManager() {
        return sqlManager;
    }

    public static StatsManager getStatsManager() {
        return statsManager;
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

    public static RankingManager getRankingManager() {
        return rankingManager;
    }

}