package hwr.oop.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import hwr.oop.application.Task;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

public class PersistenceAdapter implements SaveTaskPort, LoadTaskPort{
    private static final String FILE_PATH = "./test";

    @Override
    public List<Task> loadTasks() throws FileNotFoundException {
        return loadTasks(new FileReader(FILE_PATH));
    }

    @Override
    public List<Task> loadTasks(Reader fileReader) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        return gson.fromJson(fileReader, new TypeToken<List<Task>>() {}.getType());
    }

    @Override
    public void saveTasks(List<Task> taskList) throws IOException {
        saveTasks(taskList, new FileWriter(FILE_PATH));
    }

    @Override
    public void saveTasks(List<Task> taskList, Writer fileWriter) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        gson.toJson(taskList, fileWriter);
    }


}
