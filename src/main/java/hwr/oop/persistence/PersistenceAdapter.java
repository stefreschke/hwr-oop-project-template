package hwr.oop.persistence;

import hwr.oop.application.Task;

import java.util.List;
import java.util.UUID;

public class PersistenceAdapter implements SaveTaskPort, LoadTaskPort{

    @Override
    public Task loadTask(UUID id) {
        return null;
    }

    @Override
    public List<Task> loadTasks() {
        return null;
    }

    @Override
    public void saveTask(List<Task> list) {

    }

    @Override
    public void saveTask(Task task) {

    }
}
