package de.snx.statsapi.commands;

import de.snx.statsapi.StatsAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return true;
        }
        final Player p = (Player) sender;
        p.openInventory(StatsAPI.getRanked().getRankedInventory());
        return true;
    }
}