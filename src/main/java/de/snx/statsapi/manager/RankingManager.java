package de.snx.statsapi.manager;

import de.snx.statsapi.StatsAPI;
import de.snx.statsapi.manager.other.RankedType;
import org.bukkit.OfflinePlayer;

import java.sql.Array;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.UUID;

public class RankingManager {

    private LinkedHashMap<UUID, Integer> statsapi_kills;
    private LinkedHashMap<UUID, Integer> statsapi_deaths;
    private LinkedHashMap<UUID, Integer> statsapi_games;
    private LinkedHashMap<UUID, Integer> statsapi_wins;

    public RankingManager() {
        this.statsapi_kills = new LinkedHashMap();
        this.statsapi_deaths = new LinkedHashMap();
        this.statsapi_games = new LinkedHashMap();
        this.statsapi_wins = new LinkedHashMap();

        startUpdater();
    }

    private void startUpdater() {
        new Thread(new Runnable() {
            public void run() {
                for (;;) {
                    try {
                        PreparedStatement st = StatsAPI.getSQLManager().getConnection().prepareStatement("SELECT `UUID`,`Kills` FROM `StatsAPI` ORDER BY `Kills` DESC LIMIT 10"); 	//NULL PONTER
                        ResultSet rs = StatsAPI.getSQLManager().executeQuery(st);
                        statsapi_kills.clear();
                        while(rs.next()){
                            String nameUUID = rs.getString("UUID");
                            UUID uuid = UUID.fromString(nameUUID);
                            int kills = rs.getInt("Kills");
                            statsapi_kills.put(uuid, kills);
                            continue;
                        }
                        rs.close();
                        st.close();
                        //==============================================\\
                        PreparedStatement st2 = StatsAPI.getSQLManager().getConnection().prepareStatement("SELECT `UUID`,`Deaths` FROM `StatsAPI` ORDER BY `Deaths` DESC LIMIT 10"); 	//NULL PONTER
                        ResultSet rs2 = StatsAPI.getSQLManager().executeQuery(st2);
                        statsapi_deaths.clear();
                        while(rs2.next()){
                            String nameUUID = rs2.getString("UUID");
                            UUID uuid = UUID.fromString(nameUUID);
                            int deaths = rs2.getInt("Deaths");
                            statsapi_deaths.put(uuid, Integer.valueOf(deaths));
                            continue;
                        }
                        rs2.close();
                        st2.close();
                        //==============================================\\
                        PreparedStatement st3 = StatsAPI.getSQLManager().getConnection().prepareStatement("SELECT `UUID`,`Wins` FROM `StatsAPI` ORDER BY `Wins` DESC LIMIT 10");
                        ResultSet rs3 = StatsAPI.getSQLManager().executeQuery(st3);
                        statsapi_wins.clear();
                        while(rs3.next()){
                            String nameUUID = rs3.getString("UUID");
                            UUID uuid = UUID.fromString(nameUUID);
                            int wins = rs3.getInt("Wins");
                            statsapi_wins.put(uuid, Integer.valueOf(wins));
                            continue;
                        }
                        rs3.close();
                        st3.close();
                        //==============================================\\
                        PreparedStatement st4 = StatsAPI.getSQLManager().getConnection().prepareStatement("SELECT `UUID`,`Games` FROM `StatsAPI` ORDER BY `Games` DESC LIMIT 10");
                        ResultSet rs4 = StatsAPI.getSQLManager().executeQuery(st4);
                        statsapi_games.clear();
                        while(rs4.next()){
                            String nameUUID = rs4.getString("UUID");
                            UUID uuid = UUID.fromString(nameUUID);
                            int games = rs4.getInt("Games");
                            statsapi_games.put(uuid, Integer.valueOf(games));
                            continue;
                        }
                        rs4.close();
                        st4.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(30000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public LinkedHashMap<UUID, Integer> getStatsapi_deaths() {
        return this.statsapi_deaths;
    }

    public LinkedHashMap<UUID, Integer> getStatsapi_games() {
        return this.statsapi_games;
    }

    public LinkedHashMap<UUID, Integer> getStatsapi_wins() {
        return this.statsapi_wins;
    }

    public LinkedHashMap<UUID, Integer> getStatsapi_kills() {
        return this.statsapi_kills;
    }

    public UUID getUUID(RankedType rankedType, int position){
        UUID uuid = null;
        if(rankedType == RankedType.KILLS){

            //get the #postion# from the hashmap getStatsapi_kills!
            
        }else if(rankedType == RankedType.DEATHS){

        }else if(rankedType == RankedType.WINS){

        }else if(rankedType == RankedType.GAMES){

        }
        return uuid;
    }

    public Integer getValues(RankedType rankedType, UUID uuid){
        int value = 0;
        if(rankedType == RankedType.KILLS){
            value = getStatsapi_kills().get(uuid);
        }else if(rankedType == RankedType.DEATHS){
            value = getStatsapi_deaths().get(uuid);
        }else if(rankedType == RankedType.WINS){
            value = getStatsapi_wins().get(uuid);
        }else if(rankedType == RankedType.GAMES){
            value = getStatsapi_games().get(uuid);
        }
        return value;
    }
}