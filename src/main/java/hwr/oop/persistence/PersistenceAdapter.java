package hwr.oop.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDateTime;


public class PersistenceAdapter implements LoadPort, SavePort {

    private final AppData appData;

    public PersistenceAdapter(AppData appData) {
        this.appData = appData;
    }

    @Override
    public AppData loadData() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        try {
            return gson.fromJson(new FileReader(appData.getFilePath()), AppData.class);
        } catch (FileNotFoundException e) {
            throw new CantLoadAppDataException("AppData not at specified location: " + appData.getFilePath());
        }
    }

    @Override
    public void saveData() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .enableComplexMapKeySerialization()
                .create();
        try {
            gson.toJson(appData, new FileWriter(appData.getFilePath()));
        } catch (IOException e) {
            throw new CantSaveAppDataException("There was a Problem with saving AppData");
        }
    }
}
