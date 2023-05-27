package hwr.oop.persistence;

import java.io.FileNotFoundException;
import java.io.Reader;

public interface LoadPort {
    AppData loadData(Reader projectFile, Reader userFile) throws FileNotFoundException;
}
