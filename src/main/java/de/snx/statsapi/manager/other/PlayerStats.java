package de.snx.statsapi.manager.other;

import de.snx.statsapi.StatsAPI;
import de.snx.statsapi.mysql.DatabaseUpdate;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class PlayerStats extends DatabaseUpdate {

    private UUID uuid;
    private String name;
    private int games;
    private int wins;
    private int kills;
    private int deaths;
    private int rank;
    private boolean onlineMode;

    public PlayerStats(UUID uuid, String name) {
        this(uuid,name ,true, true);
    }

    public PlayerStats(UUID uuid, String name , boolean addUpdater, boolean onlineMode) {
        this.uuid = uuid;
        this.name = name;
        this.kills = 0;
        this.deaths = 0;
        this.rank = 0;
        this.games = 0;
        this.wins = 0;
        this.onlineMode = onlineMode;
        loadDataAsync();
        if (addUpdater) {
            setForceUpdate(true);
            addToUpdater();
        }
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }

    public int getKills() {
        return this.kills;
    }

    public int getDeaths() {
        return this.deaths;
    }

    public int getRank() {
        return this.rank;
    }

    public int getGames() {
        return this.games;
    }

    public int getWins() {
        return this.wins;
    }

    public boolean isOnlineMode() {
        return this.onlineMode;
    }

    public double getKD() {
        if (getKills() <= 0) {
            return 0.0D;
        }
        if (getDeaths() <= 0) {
            return getKills();
        }
        BigDecimal dec = new BigDecimal(getKills() / getDeaths());
        dec = dec.setScale(2, 4);
        return dec.doubleValue();
    }

    public void setName(String name) {
        this.name = name;
        setUpdate(true);
    }

    public void setGames(int games) {
        this.games = games;
        setUpdate(true);
    }

    public void setWins(int wins) {
        this.wins = wins;
        setUpdate(true);
    }

    public void setKills(int kills) {
        this.kills = kills;
        setUpdate(true);
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
        setUpdate(true);
    }

    public void setRank(int rank) {
        this.rank = rank;
        setUpdate(true);
    }

    public void saveData() {
        try {
            PreparedStatement stCheck = StatsAPI.getSQLManager().getConnection().prepareStatement("SELECT * FROM `StatsAPI` WHERE `UUID` = ?");
            stCheck.setString(1, getUUID().toString());
            ResultSet rsCheck = StatsAPI.getSQLManager().executeQuery(stCheck);
            if (!rsCheck.next()) {
                PreparedStatement st = StatsAPI.getSQLManager().getConnection().prepareStatement("INSERT INTO `StatsAPI` (UUID, Name, Games, Wins, Kills, Deaths) VALUES (?, ?, 0, 0, 0, 0)");
                st.setString(1, getUUID().toString());
                st.setString(1, getName());
                StatsAPI.getSQLManager().executeUpdate(st);
            } else {
                PreparedStatement st = StatsAPI.getSQLManager().getConnection().prepareStatement("UPDATE `StatsAPI` SET `Games` = ?, `Wins` = ?, `Kills` = ?, `Deaths` = ? WHERE `UUID` = ?");
                st.setInt(1, getGames());
                st.setInt(2, getWins());
                st.setInt(3, getKills());
                st.setInt(4, getDeaths());
                st.setString(5, getUUID().toString());
                StatsAPI.getSQLManager().executeUpdate(st);
            }
            rsCheck.close();
            stCheck.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDataAsync() {
        StatsAPI.getSQLManager().getAsyncHandler().getExecutor().execute(new Runnable() {
            public void run() {
                PlayerStats.this.saveData();
            }
        });
    }

    public void loadData() {
        try {
            PreparedStatement st = StatsAPI.getSQLManager().getConnection().prepareStatement("SELECT * FROM `StatsAPI` WHERE `UUID` = ?");
            st.setString(1, getUUID().toString());
            ResultSet rs = StatsAPI.getSQLManager().executeQuery(st);
            if (!rs.next()) {
                saveData();
            } else {
                this.name = rs.getString("Name");
                this.kills = rs.getInt("Kills");
                this.deaths = rs.getInt("Deaths");
                this.wins = rs.getInt("Wins");
                this.games = rs.getInt("Games");
            }
            ResultSet rs2 = StatsAPI.getSQLManager().executeQuery("SELECT * FROM `StatsAPI` ORDER BY `Kills` DESC");
            int count = 0;
            boolean done = false;
            while(rs2.next() && !(done)){
                count++;
                String nameUUID = rs2.getString("UUID");
                UUID uuid = UUID.fromString(nameUUID);
                if(uuid.equals(getUUID())){
                    done = true;
                    this.rank = count;
                }
            }
            rs2.close();
            rs.close();
            st.close();
            setReady(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDataAsync() {
        StatsAPI.getSQLManager().getAsyncHandler().getExecutor().execute(new Runnable() {
            public void run() {
                PlayerStats.this.loadData();
            }
        });
    }
}