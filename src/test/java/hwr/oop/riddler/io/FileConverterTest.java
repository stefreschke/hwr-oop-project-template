package hwr.oop.riddler.io;

import hwr.oop.riddler.model.Sudoku;
import org.junit.jupiter.api.Test;

import java.io.File;

class FileConverterTest {
    @Test
    void fileConverter_CellsGetRightLocation() {
        for (int i = 0; i < 45; i++) {
            String path = "src/test/resources/parser/parser." + i + ".txt";
            Sudoku sudoku = new SudokuParser().parse(new File(path));
        }
    }
}
