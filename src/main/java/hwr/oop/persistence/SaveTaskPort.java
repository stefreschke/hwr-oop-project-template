package hwr.oop.persistence;

import hwr.oop.application.Task;

import java.io.IOException;
import java.util.List;

public interface SaveTaskPort {
    void saveTasks(List<Task> list) throws IOException;
}
