package hwr.oop.application;

import java.util.List;

public interface CreateProjectUseCase {
    Project createProject(String title, List<Task> taskList, User user);
}
