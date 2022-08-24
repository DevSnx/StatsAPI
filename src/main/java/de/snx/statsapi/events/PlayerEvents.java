package de.snx.statsapi.events;

import de.snx.statsapi.StatsAPI;
import de.snx.statsapi.manager.other.PlayerStats;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerEvents implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        StatsAPI.getStatsManager().addPlayerToCache(event.getPlayer().getUniqueId(), event.getPlayer().getName());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent event) {
        StatsAPI.getStatsManager().removePlayerFromCache(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }
        Player p = (Player) event.getPlayer();
        Inventory inv = event.getInventory();
        if (event.getView().getTitle().equals("Top 10")) {
            if (event.getInventory().getItem(4).getType() == Material.PLAYER_HEAD) {
                SkullMeta meta = (SkullMeta) event.getInventory().getItem(4).getItemMeta();
                meta.setOwner(p.getName());
                List<String> lore = meta.getLore();
                if (lore == null) {
                    lore = new ArrayList<String>();
                }
                PlayerStats stats = StatsAPI.getStatsManager().getPlayerStats(p.getUniqueId());
                lore.add("§fDeine Kills §8» §a" + stats.getKills());
                lore.add("§fDeine Tode §8» §c" + stats.getDeaths());
                lore.add("§fDein Rank §8» §c" + stats.getRank());
                meta.setLore(lore);
                event.getInventory().getItem(4).setItemMeta(meta);
                return;
            }
        }
    }
}