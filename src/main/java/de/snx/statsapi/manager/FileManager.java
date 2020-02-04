package de.snx.statsapi.manager;

import de.snx.statsapi.file.DatabaseFile;
import de.snx.statsapi.file.MessagesFile;

public class FileManager {

    public DatabaseFile databaseFile;
    public MessagesFile messagesFile;

    public FileManager(){
        this.databaseFile = new DatabaseFile();
        this.messagesFile = new MessagesFile();
    }

    public DatabaseFile getDatabaseFile() {
        return databaseFile;
    }

    public MessagesFile getMessagesFile() {
        return messagesFile;
    }
}
