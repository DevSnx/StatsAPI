package de.snx.statsapi.utils;

import de.snx.statsapi.StatsAPI;
import de.snx.statsapi.manager.other.PlayerStats;
import de.snx.statsapi.manager.other.RankedType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ranked implements Listener {

    public Inventory Ranked;

    public Ranked(){
        this.Ranked = Bukkit.createInventory(null, 54, "Top 10");
        loadRankedInventory();
    }

    public void loadRankedInventory(){
        int i2 = 12;

        Ranked.setItem(4, new ItemStack(Material.PLAYER_HEAD, 1, (short) 3));

        for(int i = 0; i < 11; i++){
            if(StatsAPI.getRankingManager().getUUID(RankedType.KILLS, i) != null){
                UUID uuid = StatsAPI.getRankingManager().getUUID(RankedType.KILLS, i);
                String playerName = UUIDFetcher.getName(uuid);
                int kills = StatsAPI.getRankingManager().getValues(RankedType.KILLS, uuid);
                int deaths = StatsAPI.getRankingManager().getValues(RankedType.DEATHS, uuid);
                int rank = i;
                Ranked.setItem(i2, getPlayerHead(playerName, kills, deaths));
                i2++;
            }else{
                Bukkit.getServer().getConsoleSender().sendMessage("Rank: " + i + " = null");
            }
        }
    }

    public ItemStack getPlayerHead(String playerName, Integer kills, Integer deaths){
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwner("§a" + playerName);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§fKills §8» §a" + kills);
        lore.add("§fTode §8» §c" + deaths);
        meta.setLore(lore);
        head.setItemMeta(meta);
        return head;
    }

    public Inventory getRankedInventory() {
        return this.Ranked;
    }
}