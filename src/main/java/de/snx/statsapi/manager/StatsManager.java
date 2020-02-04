package de.snx.statsapi.manager;

import de.snx.statsapi.StatsAPI;
import de.snx.statsapi.manager.other.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class StatsManager {

    private HashMap<UUID, PlayerStats> playerStats;

    public StatsManager(){
        this.playerStats = new HashMap();
        StatsAPI.getSQLManager().executeUpdate("CREATE TABLE IF NOT EXISTS `SimpleStatsAPI` (UUID VARCHAR(100), Games INT, Wins INT, Kills INT, Deaths INT, UNIQUE KEY (UUID))");
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
