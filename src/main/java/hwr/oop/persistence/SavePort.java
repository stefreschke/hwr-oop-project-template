package hwr.oop.persistence;

import hwr.oop.application.Project;
import hwr.oop.application.Task;
import hwr.oop.application.User;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface SavePort {
    void saveData(AppData appData);
}
