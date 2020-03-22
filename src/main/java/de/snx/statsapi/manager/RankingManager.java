package de.snx.statsapi.manager;

import de.snx.statsapi.StatsAPI;
import de.snx.statsapi.manager.other.RankedType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.sql.Array;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.UUID;

public class RankingManager {

    private LinkedHashMap<UUID, Integer> statsapi_kills;
    private LinkedHashMap<Integer, UUID> statsapi_kills_ranking;
    private LinkedHashMap<UUID, Integer> statsapi_deaths;
    private LinkedHashMap<Integer, UUID> statsapi_deaths_ranking;
    private LinkedHashMap<UUID, Integer> statsapi_games;
    private LinkedHashMap<Integer, UUID> statsapi_games_ranking;
    private LinkedHashMap<UUID, Integer> statsapi_wins;
    private LinkedHashMap<Integer, UUID> statsapi_wins_ranking;

    private LinkedHashMap<UUID, Integer> statsapi_openchests;
    private LinkedHashMap<Integer, UUID> statsapi_openchests_ranking;

    private LinkedHashMap<UUID, Integer> statsapi_placedblocks;
    private LinkedHashMap<Integer, UUID> statsapi_placedblocks_ranking;

    private LinkedHashMap<UUID, Integer> statsapi_breakedblocks;
    private LinkedHashMap<Integer, UUID> statsapi_breakedblocks_ranking;

    private LinkedHashMap<UUID, Integer> statsapi_skillpoints;
    private LinkedHashMap<Integer, UUID> statsapi_skillpoints_ranking;

    public RankingManager() {
        this.statsapi_kills = new LinkedHashMap();
        this.statsapi_deaths = new LinkedHashMap();
        this.statsapi_games = new LinkedHashMap();
        this.statsapi_wins = new LinkedHashMap();
        this.statsapi_openchests = new LinkedHashMap();
        this.statsapi_placedblocks = new LinkedHashMap();
        this.statsapi_breakedblocks = new LinkedHashMap();

        this.statsapi_skillpoints = new LinkedHashMap();
        this.statsapi_skillpoints_ranking = new LinkedHashMap();
        this.statsapi_kills_ranking = new LinkedHashMap();
        this.statsapi_deaths_ranking = new LinkedHashMap();
        this.statsapi_games_ranking = new LinkedHashMap();
        this.statsapi_wins_ranking = new LinkedHashMap();
        this.statsapi_openchests_ranking = new LinkedHashMap();
        this.statsapi_placedblocks_ranking = new LinkedHashMap();
        this.statsapi_breakedblocks_ranking = new LinkedHashMap();
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
                        statsapi_kills_ranking.clear();
                        int i1 = 1;
                        while(rs.next()){
                            String nameUUID = rs.getString("UUID");
                            UUID uuid = UUID.fromString(nameUUID);
                            int kills = rs.getInt("Kills");
                            statsapi_kills.put(uuid, kills);
                            statsapi_kills_ranking.put(i1, uuid);
                            i1++;
                            continue;
                        }
                        rs.close();
                        st.close();
                        //==============================================\\
                        PreparedStatement st2 = StatsAPI.getSQLManager().getConnection().prepareStatement("SELECT `UUID`,`Deaths` FROM `StatsAPI` ORDER BY `Deaths` DESC LIMIT 10"); 	//NULL PONTER
                        ResultSet rs2 = StatsAPI.getSQLManager().executeQuery(st2);
                        statsapi_deaths.clear();
                        statsapi_deaths_ranking.clear();
                        int i2 = 1;
                        while(rs2.next()){
                            String nameUUID = rs2.getString("UUID");
                            UUID uuid = UUID.fromString(nameUUID);
                            int deaths = rs2.getInt("Deaths");
                            statsapi_deaths.put(uuid, Integer.valueOf(deaths));
                            statsapi_deaths_ranking.put(i2, uuid);
                            i2++;
                            continue;
                        }
                        rs2.close();
                        st2.close();
                        //==============================================\\
                        PreparedStatement st3 = StatsAPI.getSQLManager().getConnection().prepareStatement("SELECT `UUID`,`Wins` FROM `StatsAPI` ORDER BY `Wins` DESC LIMIT 10");
                        ResultSet rs3 = StatsAPI.getSQLManager().executeQuery(st3);
                        statsapi_wins.clear();
                        statsapi_wins_ranking.clear();
                        int i3 = 1;
                        while(rs3.next()){
                            String nameUUID = rs3.getString("UUID");
                            UUID uuid = UUID.fromString(nameUUID);
                            int wins = rs3.getInt("Wins");
                            statsapi_wins.put(uuid, Integer.valueOf(wins));
                            statsapi_wins_ranking.put(i3, uuid);
                            i3++;
                            continue;
                        }
                        rs3.close();
                        st3.close();
                        //==============================================\\
                        PreparedStatement st4 = StatsAPI.getSQLManager().getConnection().prepareStatement("SELECT `UUID`,`Games` FROM `StatsAPI` ORDER BY `Games` DESC LIMIT 10");
                        ResultSet rs4 = StatsAPI.getSQLManager().executeQuery(st4);
                        statsapi_games.clear();
                        statsapi_games_ranking.clear();
                        int i4 = 1;
                        while(rs4.next()){
                            String nameUUID = rs4.getString("UUID");
                            UUID uuid = UUID.fromString(nameUUID);
                            int games = rs4.getInt("Games");
                            statsapi_games.put(uuid, Integer.valueOf(games));
                            statsapi_games_ranking.put(i4, uuid);
                            i4++;
                            continue;
                        }
                        rs4.close();
                        st4.close();
                        //==============================================\\
                        PreparedStatement st5 = StatsAPI.getSQLManager().getConnection().prepareStatement("SELECT `UUID`,`Openchests` FROM `StatsAPI` ORDER BY `Openchests` DESC LIMIT 10");
                        ResultSet rs5 = StatsAPI.getSQLManager().executeQuery(st5);
                        statsapi_openchests.clear();
                        statsapi_openchests_ranking.clear();
                        int i5 = 1;
                        while(rs5.next()){
                            String nameUUID = rs5.getString("UUID");
                            UUID uuid = UUID.fromString(nameUUID);
                            int games = rs5.getInt("Openchests");
                            statsapi_openchests.put(uuid, Integer.valueOf(games));
                            statsapi_openchests_ranking.put(i5, uuid);
                            i5++;
                            continue;
                        }
                        rs5.close();
                        st5.close();
                        //==============================================\\
                        PreparedStatement st6 = StatsAPI.getSQLManager().getConnection().prepareStatement("SELECT `UUID`,`Placedblocks` FROM `StatsAPI` ORDER BY `Placedblocks` DESC LIMIT 10");
                        ResultSet rs6 = StatsAPI.getSQLManager().executeQuery(st6);
                        statsapi_placedblocks.clear();
                        statsapi_placedblocks_ranking.clear();
                        int i6 = 1;
                        while(rs6.next()){
                            String nameUUID = rs6.getString("UUID");
                            UUID uuid = UUID.fromString(nameUUID);
                            int blocks = rs6.getInt("Placedblocks");
                            statsapi_openchests.put(uuid, Integer.valueOf(blocks));
                            statsapi_openchests_ranking.put(i6, uuid);
                            i6++;
                            continue;
                        }
                        rs6.close();
                        st6.close();
                        //==============================================\\
                        PreparedStatement st7 = StatsAPI.getSQLManager().getConnection().prepareStatement("SELECT `UUID`,`Brokenblocks` FROM `StatsAPI` ORDER BY `Brokenblocks` DESC LIMIT 10");
                        ResultSet rs7 = StatsAPI.getSQLManager().executeQuery(st7);
                        statsapi_breakedblocks.clear();
                        statsapi_breakedblocks_ranking.clear();
                        int i7 = 1;
                        while(rs7.next()){
                            String nameUUID = rs7.getString("UUID");
                            UUID uuid = UUID.fromString(nameUUID);
                            int blocks = rs7.getInt("Brokenblocks");
                            statsapi_breakedblocks.put(uuid, Integer.valueOf(blocks));
                            statsapi_breakedblocks_ranking.put(i7, uuid);
                            i7++;
                            continue;
                        }
                        rs7.close();
                        st7.close();
                        //==============================================\\
                        PreparedStatement st8 = StatsAPI.getSQLManager().getConnection().prepareStatement("SELECT `UUID`,`Skillpoints` FROM `StatsAPI` ORDER BY `Skillpoints` DESC LIMIT 10");
                        ResultSet rs8 = StatsAPI.getSQLManager().executeQuery(st8);
                        statsapi_skillpoints.clear();
                        statsapi_skillpoints_ranking.clear();
                        int i8 = 1;
                        while(rs8.next()){
                            String nameUUID = rs8.getString("UUID");
                            UUID uuid = UUID.fromString(nameUUID);
                            int blocks = rs8.getInt("Skillpoints");
                            statsapi_skillpoints.put(uuid, Integer.valueOf(blocks));
                            statsapi_skillpoints_ranking.put(i8, uuid);
                            i8++;
                            continue;
                        }
                        rs8.close();
                        st8.close();
                        //==============================================\\
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

    private LinkedHashMap<UUID, Integer> getStatsapi_deaths() {
        return this.statsapi_deaths;
    }

    private LinkedHashMap<UUID, Integer> getStatsapi_games() {
        return this.statsapi_games;
    }

    private LinkedHashMap<UUID, Integer> getStatsapi_wins() {
        return this.statsapi_wins;
    }

    private LinkedHashMap<UUID, Integer> getStatsapi_kills() {
        return this.statsapi_kills;
    }

    public LinkedHashMap<UUID, Integer> getStatsapi_openchests() {
        return this.statsapi_openchests;
    }

    public LinkedHashMap<UUID, Integer> getStatsapi_placedblocks() {
        return this.statsapi_placedblocks;
    }

    public LinkedHashMap<UUID, Integer> getStatsapi_breakedblocks() {
        return this.statsapi_breakedblocks;
    }

    private LinkedHashMap<Integer, UUID> getStatsapi_deaths_ranking() {
        return this.statsapi_deaths_ranking;
    }

    private LinkedHashMap<Integer, UUID> getStatsapi_games_ranking() {
        return this.statsapi_games_ranking;
    }

    private LinkedHashMap<Integer, UUID> getStatsapi_kills_ranking() {
        return this.statsapi_kills_ranking;
    }

    private LinkedHashMap<Integer, UUID> getStatsapi_wins_ranking() {
        return this.statsapi_wins_ranking;
    }

    public LinkedHashMap<Integer, UUID> getStatsapi_openchests_ranking() {
        return this.statsapi_openchests_ranking;
    }

    public LinkedHashMap<Integer, UUID> getStatsapi_placedblocks_ranking() {
        return this.statsapi_placedblocks_ranking;
    }

    public LinkedHashMap<Integer, UUID> getStatsapi_skillpoints_ranking() {
        return this.statsapi_skillpoints_ranking;
    }

    public LinkedHashMap<UUID, Integer> getStatsapi_skillpoints() {
        return this.statsapi_skillpoints;
    }

    public LinkedHashMap<Integer, UUID> getStatsapi_breakedblocks_ranking() {
        return this.statsapi_breakedblocks_ranking;
    }

    public UUID getUUID(RankedType rankedType, int position){
        UUID uuid = null;
        if(rankedType == RankedType.KILLS){
            uuid = getStatsapi_kills_ranking().get(position);
        }else if(rankedType == RankedType.DEATHS){
            uuid = getStatsapi_deaths_ranking().get(position);
        }else if(rankedType == RankedType.WINS){
            uuid = getStatsapi_wins_ranking().get(position);
        }else if(rankedType == RankedType.GAMES){
            uuid = getStatsapi_games_ranking().get(position);
        }else if(rankedType == RankedType.OPENCHESTS){
            uuid = getStatsapi_openchests_ranking().get(position);
        }else if(rankedType == RankedType.PLACEDBLOCKS){
            uuid = getStatsapi_placedblocks_ranking().get(position);
        }else if(rankedType == RankedType.BREAKEDBLOCKS){
            uuid = getStatsapi_breakedblocks_ranking().get(position);
        }else if(rankedType == RankedType.SKILLPOINTS){
            uuid = getStatsapi_skillpoints_ranking().get(position);
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
        }else if(rankedType == RankedType.OPENCHESTS){
            value = getStatsapi_openchests().get(uuid);
        }else if(rankedType == RankedType.PLACEDBLOCKS){
            value = getStatsapi_placedblocks().get(uuid);
        }else if(rankedType == RankedType.BREAKEDBLOCKS){
            value = getStatsapi_breakedblocks().get(uuid);
        }else if(rankedType == RankedType.SKILLPOINTS){
            value = getStatsapi_skillpoints().get(uuid);
        }
        return value;
    }
}