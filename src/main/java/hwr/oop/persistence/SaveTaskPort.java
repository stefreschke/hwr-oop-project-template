package hwr.oop.persistence;

import hwr.oop.application.Task;

public interface SaveTaskPort {
    void saveTask(Task task);
}
