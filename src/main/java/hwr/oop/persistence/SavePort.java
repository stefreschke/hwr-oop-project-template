package hwr.oop.persistence;

import java.io.Writer;

public interface SavePort {
    void saveData(AppData appData, Writer fileWriter);
}
