package de.snx.simplestats.manager.other;

import com.google.common.math.Stats;

import java.math.BigDecimal;
import java.util.UUID;

public class StatsData {

    private UUID uuid;
    private int kills;
    private int deaths;
    private long timeCreated;
    private long playtime;
    private boolean onlineMode;

    public StatsData(UUID uuid){
        this(uuid, true, true);
    }

    public StatsData(UUID uuid, boolean addUpdater, boolean onlineMode){
        this.uuid = uuid;
        this.kills = 0;
        this.deaths = 0;
        this.playtime = 0L;
        this.onlineMode = onlineMode;
        this.timeCreated = System.currentTimeMillis();
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public int getKills() {
        return this.kills;
    }

    public int getDeaths() {
        return this.deaths;
    }

    public long getPlaytime() {
        return this.playtime;
    }

    public long getTimeCreated() {
        return this.timeCreated;
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

    private void calculateNewPlaytime() {
        long currentTime = System.currentTimeMillis();
        long timeDiff = currentTime - this.timeCreated;
        this.playtime += timeDiff;
        this.timeCreated = currentTime;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
}