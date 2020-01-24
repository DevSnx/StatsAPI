package de.snx.simplestats.manager;

import de.snx.simplestats.manager.other.StatsData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SimpleStatsManager {

    private HashMap<UUID, StatsData> playerStats;

    public void loadStatsForOnlines() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            addPlayerToCache(all.getUniqueId());
        }
    }

    public void addPlayerToCache(UUID uuid) {
        if (this.playerStats.containsKey(uuid)) {
            return;
        }
        this.playerStats.put(uuid, new StatsData(uuid));
    }

    public void removePlayerFromCache(UUID uuid) {
        if (!this.playerStats.containsKey(uuid)) {
            return;
        }
        StatsData stats = (StatsData) this.playerStats.get(uuid);
        this.playerStats.remove(uuid);
    }

    public StatsData getPlayerStats(UUID uuid) {
        if (!this.playerStats.containsKey(uuid)) {
            return null;
        }
        return (StatsData) this.playerStats.get(uuid);
    }
}