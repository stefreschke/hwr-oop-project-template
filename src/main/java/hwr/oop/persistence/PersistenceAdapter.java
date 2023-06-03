package hwr.oop.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDateTime;


public class PersistenceAdapter implements LoadPort, SavePort {

    private final String directory;
    public PersistenceAdapter(String directory) {
        this.directory = directory;
    }

    @Override
    public AppData loadData() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        try {
            return gson.fromJson(new FileReader(directory + AppData.class), AppData.class);
        } catch (FileNotFoundException e) {
            throw new CantLoadAppDataException("AppData not at specified location:");
        }
    }

    @Override
    public void saveData(AppData appData) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .enableComplexMapKeySerialization()
                .create();
        try {
            gson.toJson(appData, new FileWriter(directory + AppData.class));
        } catch (IOException e) {
            throw new CantSaveAppDataException("There was a Problem with saving AppData");
        }
    }
}
