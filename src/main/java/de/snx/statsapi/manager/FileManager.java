package de.snx.statsapi.manager;

import de.snx.statsapi.file.DatabaseFile;

public class FileManager {

    public DatabaseFile databaseFile;

    public FileManager(){
        this.databaseFile = new DatabaseFile();
    }

    public DatabaseFile getDatabaseFile() {
        return databaseFile;
    }
}
