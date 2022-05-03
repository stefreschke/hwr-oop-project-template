package hwr.oop.riddler.io;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroup;

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
        StringBuilder builder = new StringBuilder();
        builder.append("------ Solved Sudoku -------\n");
        for (CellGroup row : sudoku.getRows()) {
            for (Cell cell : row.getCells()) {
                builder.append(cell.isFilled() ? cell.getValue() : "_");
                builder.append(" ");
            }
            builder.append("\n");
        }
        builder.append("----------------------------\n");
        return builder.toString();
    }
}
