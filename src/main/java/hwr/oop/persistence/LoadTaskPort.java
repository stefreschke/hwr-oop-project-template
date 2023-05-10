package hwr.oop.persistence;

import hwr.oop.application.Task;
import hwr.oop.input.TaskListException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public interface LoadTaskPort {
    List<Task> loadTasks() throws FileNotFoundException;

    default Task loadTask(UUID id) throws FileNotFoundException {
        List<Task> taskList = loadTasks();
        return taskList.stream()
                .filter(tmpTask -> tmpTask.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new TaskListException(String.format("task %s does not exist", id.toString())));
    }
}
