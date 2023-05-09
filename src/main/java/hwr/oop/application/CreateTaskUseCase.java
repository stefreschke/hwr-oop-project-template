package hwr.oop.application;

import java.time.LocalDateTime;
import java.util.List;

public interface CreateTaskUseCase {
    void createTask(int id, String title, String content, TaskState state, List<TaskTag> tagList, User creator,
                    LocalDateTime deadline);
}
