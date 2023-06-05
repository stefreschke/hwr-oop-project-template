package hwr.oop.application;

import hwr.oop.persistence.AppData;
import hwr.oop.persistence.SavePort;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CreateProjectService implements CreateProjectUseCase {
    @Override
    public void createProject(SavePort save, AppData appData, String title, List<Task> taskList, User user) {
        Project project = new Project(UUID.randomUUID(), taskList, title, Map.of(user, Boolean.TRUE));
        appData.addProject(project);
        save.saveData(appData);
    }
}
