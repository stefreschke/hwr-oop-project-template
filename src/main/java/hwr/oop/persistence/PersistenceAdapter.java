package hwr.oop.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hwr.oop.ToDoList;
import hwr.oop.util.LocalDateTypeAdapter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static hwr.oop.ToDoList.linkToCorrectBucket;

public class PersistenceAdapter implements SavePort, LoadPort {
    private final String directory;
    public PersistenceAdapter() {
        this.directory = "./";
    }
    @Override
    public void saveData(ToDoList toDoList) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        try (FileWriter fileWriter = new FileWriter("data.json")) {
            fileWriter.write(gson.toJson(toDoList));
        } catch (IOException e) {
            throw new PersistenceFileNotFoundException("Sorry...File could not be neither found nor created.");
        }
    }
    @Override
    public ToDoList loadData() {
        File file = new File(directory + "data.json");
        if (file.exists() && file.length() == 0) {
            return new ToDoList("myList");
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        ToDoList toDoList;
        try {
            toDoList = gson.fromJson(new FileReader(file), ToDoList.class);
        } catch (java.io.FileNotFoundException e) {
            throw new PersistenceFileNotFoundException("AppData not at specified location:");
        }
        if (toDoList != null) {
            if (toDoList.getItems() == null) {
                toDoList.setItems(new ArrayList<>());
            }
            linkToCorrectBucket(toDoList);
        }
        return toDoList;
    }
}