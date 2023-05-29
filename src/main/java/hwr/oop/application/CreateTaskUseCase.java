package hwr.oop.application;

import java.time.LocalDateTime;
import java.util.List;

public interface CreateTaskUseCase {
    void createTask(String title, String content, TaskState state, LocalDateTime deadline);
}
