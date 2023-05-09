package hwr.oop.group4.todo.persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileAdapter implements LoadPersistenceAdapter, SavePersistenceAdapter {

    @Override
    public void save(Persistable data, File file) {
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(data.exportAsString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Persistable load(File file) {
        return null;
    }
}
