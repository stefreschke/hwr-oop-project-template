package hwr.oop.group4.todo.persistence;

import java.io.File;

public interface SavePersistenceAdapter {

    void save(Persistable data, File file);
}
