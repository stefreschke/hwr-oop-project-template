package hwr.oop.persistence;

import hwr.oop.application.Task;

import java.util.List;

public class PersistenceAdapter implements SaveTaskPort, LoadTaskPort{

    @Override
    public Task loadTask(int id) {
        return null;
    }

    @Override
    public List<Task> loadTask() {
        return null;
    }

    @Override
    public void saveTask(List<Task> list) {

    }

    @Override
    public void saveTask(Task task) {

    }
}
