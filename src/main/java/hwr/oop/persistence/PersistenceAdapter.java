package hwr.oop.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hwr.oop.application.Task;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PersistenceAdapter implements SaveTaskPort, LoadTaskPort{
    private static final String FILE_PATH = "./test";

    @Override
    public List<Task> loadTasks() throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(new FileReader(FILE_PATH), new TypeToken<List<Task>>() {}.getType());
    }

    @Override
    public void saveTasks(List<Task> taskList) throws IOException {
        Gson gson = new Gson();
        gson.toJson(taskList, new FileWriter(FILE_PATH));
    }


}
