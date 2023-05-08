package hwr.oop.group4.todo.persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileAdapter implements LoadPersistenceAdapter, SavePersistenceAdapter {

    private final File file;

    public FileAdapter(File file) {
        this.file = file;
    }

    @Override
    public void save(Persistable data) {
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(data.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Persistable load() {
        return null;
    }
}
