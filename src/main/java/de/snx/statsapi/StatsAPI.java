package de.snx.statsapi;

import de.snx.statsapi.commands.CommandStats;
import de.snx.statsapi.commands.CommandTop;
import de.snx.statsapi.events.PlayerEvents;
import de.snx.statsapi.manager.FileManager;
import de.snx.statsapi.manager.RankingManager;
import de.snx.statsapi.manager.StatsManager;
import de.snx.statsapi.mysql.SQLManager;
import de.snx.statsapi.utils.Ranked;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
public class StatsAPI extends JavaPlugin {
    public static StatsAPI instance;
    public static StatsManager statsManager;
    public static SQLManager sqlManager;
    public static FileManager fileManager;
    public static RankingManager rankingManager;
    public static Ranked ranked;
    @Override
    public void onEnable() {
        instance = this;
        fileManager = new FileManager();
        Bukkit.getServer().getConsoleSender().sendMessage("§7+----------------------------------------------------+");
        Bukkit.getServer().getConsoleSender().sendMessage("§7|                                                    §7|");
        Bukkit.getServer().getConsoleSender().sendMessage("§7|         §cStatsAPI by DevSnx and AJAR3TR0          §7|");
        Bukkit.getServer().getConsoleSender().sendMessage("§7|                        §bV: §e" + getInstance().getDescription().getVersion() + "                    §7|");
        Bukkit.getServer().getConsoleSender().sendMessage("§7|                                                    §7|");
        if (loadSQL()) {
            statsManager = new StatsManager();
            rankingManager = new RankingManager();

            if(getFileManager().getConfigFile().getStats() == true){
                getCommand("stats").setExecutor(new CommandStats());
            }
            if(getFileManager().getConfigFile().getTop() == true){
                getCommand("top").setExecutor(new CommandTop());
            }
            Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);

            Bukkit.getServer().getConsoleSender().sendMessage("§7|                  §aLoading success!              §7|");
        }else{
            Bukkit.getServer().getConsoleSender().sendMessage("§7|           §cNo Database Connection!      §7|");
        }
        Bukkit.getServer().getConsoleSender().sendMessage("§7|                                                    §7|");
        Bukkit.getServer().getConsoleSender().sendMessage("§7+----------------------------------------------------+");
        Bukkit.getScheduler().runTaskLater(getInstance(), new Runnable() {
            @Override
            public void run() {
                ranked = new Ranked();
            }
        }, 60L);
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

    public static Ranked getRanked() {
        return ranked;
    }
}