package de.snx.simplestats.manager;

import de.snx.simplestats.file.DatabaseFile;

public class FileManager {

    public DatabaseFile databaseFile;

    public FileManager(){
        this.databaseFile = new DatabaseFile();
    }

    public DatabaseFile getDatabaseFile() {
        return databaseFile;
    }
}
