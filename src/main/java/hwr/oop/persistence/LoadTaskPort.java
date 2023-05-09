package hwr.oop.persistence;

import hwr.oop.application.Task;

import java.util.List;

public interface LoadTaskPort {
    Task loadTask(int id);

    List<Task> loadTask();
}
