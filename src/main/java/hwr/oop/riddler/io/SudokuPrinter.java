package hwr.oop.riddler.io;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.CellPosition;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class SudokuPrinter {
    private final BufferedWriter writer;

    public SudokuPrinter(OutputStream out) {
        this.writer = new BufferedWriter(new OutputStreamWriter(out));
    }

    public void print(Sudoku sudoku) {
        try {
            writer.write(sudokuToString(sudoku));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String sudokuToString(Sudoku sudoku) {
        var builder = new StringBuilder();

        builder.append("------ Solved Sudoku -------\n");
        for (int x = 0; x < sudoku.getSize(); x++) {
            for (int y = 0; y < sudoku.getSize(); y++) {
                var cellOptional = sudoku.getCellAt(new CellPosition(x, y));
                //TODO other way
                assert cellOptional.isPresent();
                var cell = cellOptional.get();
                builder.append((cell.isFilled() ? cell.getValue() : "_"));
                builder.append(" ");

            }
            builder.append("\n");
        }

        builder.append("----------------------------\n");
        return builder.toString();
    }
}
