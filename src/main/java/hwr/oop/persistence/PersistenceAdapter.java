package hwr.oop.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import hwr.oop.application.Project;
import hwr.oop.application.User;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.List;

public class PersistenceAdapter implements LoadPort, SavePort {
    @Override
    public AppData loadData(Reader projectFile, Reader userFile) throws FileNotFoundException {
        List<Project> projectList = loadProjects(projectFile);
        List<User> userList = loadUsers(userFile);
        return new AppData(projectList, userList);
    }

    public List<Project> loadProjects(Reader projectFile) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        return gson.fromJson(projectFile, new TypeToken<List<Project>>() {}.getType());
    }

    public List<User> loadUsers(Reader userFile) {
        return new Gson().fromJson(userFile, new TypeToken<List<User>>() {}.getType());
    }

    @Override
    public void saveData(AppData appData, Writer projectFile, Writer userFile) {
        saveProjects(appData.getProjectList(), projectFile);
        saveUsers(appData.getUserList(), userFile);
    }

    public void saveProjects(List<Project> projectList, Writer projectFile) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        gson.toJson(projectList, projectFile);
    }

    public void saveUsers(List<User> userList, Writer userFile) {
        new Gson().toJson(userList, userFile);
    }
}
