package de.snx.simplestats.manager;

import de.snx.simplestats.SimpleStats;
import de.snx.simplestats.manager.other.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SimpleStatsManager {

    private HashMap<UUID, PlayerStats> playerStats;

    public SimpleStatsManager(){
        this.playerStats = new HashMap();
        SimpleStats.getSQLManager().executeUpdate("CREATE TABLE IF NOT EXISTS `SimpleStatsAPI` (UUID VARCHAR(100), Games INT, Wins INT, Kills INT, Deaths INT, Playtime BIGINT, UNIQUE KEY (UUID))");
    }

    public void loadStatsForOnlines() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            addPlayerToCache(all.getUniqueId());
        }
    }

    public void addPlayerToCache(UUID uuid) {
        if (this.playerStats.containsKey(uuid)) {
            return;
        }
        this.playerStats.put(uuid, new PlayerStats(uuid));
    }

    public void removePlayerFromCache(UUID uuid) {
        if (!this.playerStats.containsKey(uuid)) {
            return;
        }
        PlayerStats stats = (PlayerStats) this.playerStats.get(uuid);
        this.playerStats.remove(uuid);
    }

    public PlayerStats getPlayerStats(UUID uuid) {
        if (!this.playerStats.containsKey(uuid)) {
            return null;
        }
        return (PlayerStats) this.playerStats.get(uuid);
    }
}