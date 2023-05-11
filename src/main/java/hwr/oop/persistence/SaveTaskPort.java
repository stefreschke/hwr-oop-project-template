package hwr.oop.persistence;

import hwr.oop.application.Task;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface SaveTaskPort {
    void saveTasks(List<Task> taskList) throws IOException;

    void saveTasks(List<Task> taskList, Writer fileWriter) throws IOException;
}
