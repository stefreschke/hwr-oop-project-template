package hwr.oop.persistence;

import hwr.oop.application.Project;
import hwr.oop.application.Task;
import hwr.oop.input.TaskListException;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.List;
import java.util.UUID;

public interface LoadPort {
    AppData loadData();
}
