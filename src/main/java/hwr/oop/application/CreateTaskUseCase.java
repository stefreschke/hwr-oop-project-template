package hwr.oop.application;

import java.time.LocalDateTime;

public interface CreateTaskUseCase {
    Task createTaskInProject(String title, String content, TaskState taskState, LocalDateTime deadLine, Project project);
    Task createTaskInContextList(String title, String content, TaskState taskState, LocalDateTime deadLine,User user);
}
