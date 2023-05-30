package hwr.oop.persistenceTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import hwr.oop.application.Project;
import hwr.oop.application.Task;
import hwr.oop.application.TaskState;
import hwr.oop.application.User;
import hwr.oop.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PersistenceTest {

    LoadPort load = new PersistenceAdapter();
    SavePort save = new PersistenceAdapter();
    AppData appData;

    StringWriter appDataJsonWriter = new StringWriter();

    StringReader appDataJsonReader;


    @BeforeEach
    void setUp() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("testTitle1", "testContent1", TaskState.IN_PROGRESS, LocalDateTime.now()));
        taskList.add(new Task("testTitle2", "testContent2", TaskState.IN_REVIEW, LocalDateTime.now()));
        taskList.add(new Task("testTitle3", "testContent3", TaskState.DONE, LocalDateTime.now()));

        List<User> userList = new ArrayList<>();
        userList.add(new User("testUser1", new ArrayList<>(), new ArrayList<>()));
        userList.add(new User("testUser2", new ArrayList<>(), new ArrayList<>()));
        userList.add(new User("testUser3", new ArrayList<>(), new ArrayList<>()));

        List<Project> projectList = new ArrayList<>();
        projectList.add(new Project(taskList, "testProject1", Map.of(userList.get(0), Boolean.TRUE)));
        projectList.add(new Project(taskList, "testProject2", Map.of(userList.get(1), Boolean.TRUE)));
        projectList.add(new Project(taskList, "testProject3", Map.of(userList.get(2), Boolean.FALSE)));

        appData = new AppData(projectList, userList);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .enableComplexMapKeySerialization()
                .create();
        gson.toJson(appData, appDataJsonWriter);
        appDataJsonReader = new StringReader(appDataJsonWriter.toString());
    }

    @Test
    void CanLoadAppData() {
        try {
            AppData result = load.loadData(appDataJsonReader);
            assertThat(result.equals(appData));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    void CanSaveAppData() {
        StringWriter testDataJson = new StringWriter();
        save.saveData(appData, testDataJson);
        assertThat(testDataJson.toString()).hasToString(appDataJsonWriter.toString());
    }
}
