package hwr.oop.application;

import hwr.oop.persistence.AppData;

import java.util.List;
import java.util.Map;

public interface CreateProjectUseCase {
    void createProject(AppData appData, String title, List<Task> taskList, Map<User, Boolean> permissions);
}
