package de.snx.statsapi.commands;

import de.snx.statsapi.StatsAPI;
import de.snx.statsapi.manager.other.PlayerStats;
import de.snx.statsapi.mysql.DatabaseUpdate;
import de.snx.statsapi.utils.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandStats implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            return true;
        }
        final Player p = (Player) sender;
        if (args.length > 1) {
            p.sendMessage(StatsAPI.getFileManager().getMessagesFile().getString("MESSAGE.STATS.ONKOWNCOMMAND"));
            return true;
        }
        if (args.length == 0)
            sendStats(p, (OfflinePlayer)p);
        if (args.length == 1) {
            Player targetOnline = Bukkit.getPlayer(args[0]);
            if (targetOnline != null) {
                sendStats(p, (OfflinePlayer)targetOnline);
            } else {
                UUIDFetcher.getUUID(args[0], new UUIDFetcher.Consumer<UUID>() {
                    public void accept(UUID uuid) {
                        if (uuid == null) {
                            p.sendMessage(StatsAPI.getFileManager().getMessagesFile().getString("MESSAGE.STATS.NOPLAYER"));
                        } else {
                            if(StatsAPI.getStatsManager().hasPlayerStats(uuid)){
                                OfflinePlayer opTarget = Bukkit.getOfflinePlayer(uuid);
                                CommandStats.this.sendStats(p, opTarget);
                            }else{
                                p.sendMessage(StatsAPI.getFileManager().getMessagesFile().getString("MESSAGE.STATS.NOSTATS"));
                                return;
                            }
                        }
                    }
                });
            }
        }
        return true;
    }

    private void sendStats(final Player p, final OfflinePlayer target) {
        final PlayerStats stats = !target.isOnline() ? new PlayerStats(target.getUniqueId(), target.getName(),false, false) : StatsAPI.getStatsManager().getPlayerStats(target.getUniqueId());
        if (stats == null) {
            p.sendMessage(StatsAPI.getFileManager().getMessagesFile().getString("MESSAGE.STATS.NOSTATS"));
            return;
        }
        stats.addReadyExecutor(new DatabaseUpdate.ReadyExecutor() {
            @Override
            public void ready() {
                for(String abc : StatsAPI.getFileManager().getMessagesFile().getConfig().getStringList("MESSAGE.STATS.COMMAND")){
                    abc = abc.replace("%KILLS%" , String.valueOf(stats.getKills()));
                    abc = abc.replace("%GAMES%" , String.valueOf(stats.getGames()));
                    abc = abc.replace("%WINS%" , String.valueOf(stats.getWins()));
                    abc = abc.replace("%DEATHS%" , String.valueOf(stats.getDeaths()));
                    //abc = abc.replace("%KD%" , String.valueOf(stats.getKD()));
                    abc = abc.replace("%RANK%" , String.valueOf(stats.getRank()));
                    abc = abc.replace("%PLACEDBLOCKS%" , String.valueOf(stats.getPlacedblocks()));
                    abc = abc.replace("%BROKENBLOCKS%" , String.valueOf(stats.getBrokenblocks()));
                    abc = abc.replace("%OPENCHESTS%" , String.valueOf(stats.getOpenchests()));
                    abc = abc.replace("%PLAYERNAME%" , String.valueOf(stats.getName()));
                    abc = abc.replace("%ELO%" , String.valueOf(stats.getElo()));
                    abc = abc.replace("&", "§");
                    p.sendMessage(abc);
                }
            }
        });
    }
}