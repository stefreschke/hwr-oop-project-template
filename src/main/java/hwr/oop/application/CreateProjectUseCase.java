package hwr.oop.application;

import java.util.List;
import java.util.Map;

public interface CreateProjectUseCase {
    void createProject(String title, List<Task> taskList, Map<User, Boolean> permissions);
}
