package hwr.oop.application;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CreateTaskUseCase {
    UUID createTaskInProject(String title, String content, TaskState taskState, LocalDateTime deadLine, Project project);
    UUID createTaskInContextList(String title, String content, TaskState taskState, LocalDateTime deadLine,User user);

}
