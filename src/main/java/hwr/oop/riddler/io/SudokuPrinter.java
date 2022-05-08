package hwr.oop.riddler.io;

import hwr.oop.riddler.model.Sudoku;

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
            writer.write(sudokuToString(sudoku.getIntArray()));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String sudokuToString(int[][] sudoku) {
        var builder = new StringBuilder();


        builder.append("------ Solved Sudoku -------\n");
        for (int[] row : sudoku) {
            for (int value : row) {
                builder.append((value == 0 ? "_" : value));
                builder.append(" ");
            }
            builder.append("\n");
        }
        builder.append("----------------------------\n");
        return builder.toString();
    }
}
