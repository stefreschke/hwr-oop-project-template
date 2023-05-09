package hwr.oop.persistence;

import hwr.oop.application.Task;

import java.util.List;

public interface SaveTaskPort {
    void saveTask(List<Task> list);
    void saveTask(Task task);
}
