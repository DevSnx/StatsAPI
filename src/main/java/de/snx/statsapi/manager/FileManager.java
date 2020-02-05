package de.snx.statsapi.manager;

import de.snx.statsapi.file.ConfigFile;
import de.snx.statsapi.file.DatabaseFile;
import de.snx.statsapi.file.MessagesFile;

public class FileManager {

    public DatabaseFile databaseFile;
    public MessagesFile messagesFile;
    public ConfigFile configFile;

    public FileManager(){
        this.databaseFile = new DatabaseFile();
        this.messagesFile = new MessagesFile();
        this.configFile = new ConfigFile();
    }

    public DatabaseFile getDatabaseFile() {
        return databaseFile;
    }

    public MessagesFile getMessagesFile() {
        return messagesFile;
    }

    public ConfigFile getConfigFile() {
        return configFile;
    }

}