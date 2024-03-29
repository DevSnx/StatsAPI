package de.devsnx.statsapi.manager;

import de.devsnx.statsapi.StatsAPI;
import de.devsnx.statsapi.manager.other.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class StatsManager {

    private HashMap<UUID, PlayerStats> playerStats;

    public StatsManager(){
        this.playerStats = new HashMap();
        StatsAPI.getSQLManager().executeUpdate("CREATE TABLE IF NOT EXISTS `StatsAPI` (UUID VARCHAR(100), Name VARCHAR(100), Games INT, Wins INT, Kills INT, Deaths INT, Placedblocks INT, Brokenblocks INT, Openchests INT, Skillpoints INT, Elo INT , UNIQUE KEY (UUID))");
    }

    public void loadStatsForOnlines() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            addPlayerToCache(all.getUniqueId(), all.getName());
        }
    }

    public boolean hasPlayerStats(UUID uuid){
        boolean check = false;
        try {
            PreparedStatement st = StatsAPI.getSQLManager().getConnection().prepareStatement("SELECT * FROM `StatsAPI` WHERE `UUID` = ?");
            st.setString(1, uuid.toString());
            ResultSet rs = StatsAPI.getSQLManager().executeQuery(st);
            if(rs.next()){
               check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public void addPlayerToCache(UUID uuid, String name) {
        if (this.playerStats.containsKey(uuid)) {
            return;
        }
        this.playerStats.put(uuid, new PlayerStats(uuid, name));
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