package hwr.oop.application;

import java.util.List;

public interface LoadTaskPort {
    Task loadTask(int id);

    List<Task> loadTask();
}
