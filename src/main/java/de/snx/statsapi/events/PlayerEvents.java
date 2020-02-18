package de.snx.statsapi.events;

import de.snx.statsapi.StatsAPI;
import de.snx.statsapi.manager.other.PlayerStats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {

    @EventHandler (priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event){
        StatsAPI.getStatsManager().addPlayerToCache(event.getPlayer().getUniqueId(), event.getPlayer().getName());
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent event){
        StatsAPI.getStatsManager().removePlayerFromCache(event.getPlayer().getUniqueId());
    }
}