package hwr.oop.application;

import hwr.oop.persistence.AppData;
import hwr.oop.persistence.SavePort;
import java.util.List;

public interface CreateProjectUseCase {
    void createProject(SavePort save, AppData appData, String title, List<Task> taskList, User user);
}
