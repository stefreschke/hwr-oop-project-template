package hwr.oop.application;

import java.time.LocalDateTime;

public interface CreateTaskUseCase {
    void createTaskInProject(String title, String content, TaskState taskState, LocalDateTime deadLine,Project project);
    void createTaskInContextList(String title, String content, TaskState taskState, LocalDateTime deadLine,User user);

}
