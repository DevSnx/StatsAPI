package de.snx.statsapi.file;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MessagesFile extends FileBase{

    public MessagesFile() {
        super("", "messages");
        writeDefaults();
    }

    private void writeDefaults() {
        FileConfiguration cfg = getConfig();
        cfg.addDefault("MESSAGE.STATS.PREFIX", "&8[&7StatsAPI&8]");
        cfg.addDefault("MESSAGE.STATS.NOSTATS", "%PREFIX% &cNo Stats available");
        cfg.addDefault("MESSAGE.STATS.NOPLAYER", "%PREFIX% &cNo Player with this Minecraftname exists");
        cfg.addDefault("MESSAGE.STATS.ONKOWNCOMMAND", "%PREFIX% &cuse: &e/stats [player]");

        List<String> list = new ArrayList();
        list.add("&7-=-=] &eStats %PLAYERNAME% &7[=-=-");
        list.add(" &7Games: &e%GAMES%");
        list.add(" &7Wins: &e%WINS%");
        list.add(" &7Kills: &e%KILLS%");
        list.add(" &7Deaths: &e%DEATHS%");
        list.add(" &7K/D: &e%KD%");
        list.add(" &7Open Chests: &e%OPENCHESTS%");
        list.add(" &7Broken Blocks: &e%BROKENBLOCKS%");
        list.add(" &7Placed Blocks: &e%PLACEDBLOCKS%");
        list.add(" &7Elo: &e%ELO%");
        list.add("&7-=-=-=-=-=-=-=-=-=-=-=-");
        cfg.addDefault("MESSAGE.STATS.COMMAND", list);
        cfg.options().copyDefaults(true);
        saveConfig();
    }

    public String getString(String path){
        String message = getConfig().getString(path);
        message = message.replace("&", "§");
        if(message.contains("%PREFIX%")){
            message = message.replace("%PREFIX%", getString("MESSAGE.STATS.PREFIX"));
        }
        return message;
    }
}