package hwr.oop.application;

import hwr.oop.persistence.AppData;
import hwr.oop.persistence.PersistenceAdapter;
import hwr.oop.persistence.SavePort;

import java.util.List;
import java.util.Map;

public class CreateProjectService implements CreateProjectUseCase{

    private AppData appData;
    SavePort save;

    public CreateProjectService(AppData appData) {
        this.appData = appData;
        save = new PersistenceAdapter(appData);
    }

    @Override
    public void createProject(String title, List<Task> taskList, Map<User, Boolean> permissions) {
        appData.addProject(new Project(taskList, title, permissions));
        save.saveData();
    }
}
