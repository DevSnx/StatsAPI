package de.snx.simplestats.manager.other;

import com.google.common.math.Stats;

import java.math.BigDecimal;
import java.util.UUID;

public class StatsData {

    private UUID uuid;
	private int games;
	private int wins;
    private int kills;
    private int deaths;
	private int rank;
    private boolean onlineMode;

    public StatsData(UUID uuid){
        this(uuid, true, true);
    }

    public StatsData(UUID uuid, boolean addUpdater, boolean onlineMode){
        this.uuid = uuid;
        this.games = 0;
		this.wins = 0;
		this.kills = 0;
        this.deaths = 0;
		this.rank = 0;
        this.onlineMode = onlineMode;
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

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
}