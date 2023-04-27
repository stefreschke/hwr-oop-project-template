package hwr.oop.group4.todo.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FileAdapterTest {

    @Test
    void save(@TempDir Path path) {
        Persisted data = mock();
        when(data.toString()).thenReturn("demo file content");

        File file = new File(path.toString() + "/FileAdapterTest.save.txt");
        System.out.println(file.getPath());

        FileAdapter fileAdapter = new FileAdapter(file);
        fileAdapter.save(data);
    }

    @Test
    void load() {
    }
}
