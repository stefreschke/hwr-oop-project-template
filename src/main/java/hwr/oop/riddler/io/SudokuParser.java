package hwr.oop.riddler.io;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellPosition;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SudokuParser {
    public Sudoku parse(File inputFile) {
        return new Sudoku(parseInputFile(inputFile));
    }

    public Sudoku parse(int[][] sudokuArray) {
        return new Sudoku(parseArray(sudokuArray));
    }

    private Set<Cell> parseInputFile(File inputFile) {
        Set<Cell> cells = new HashSet<>();
        try (var bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            int rowIndex = 0;
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                var sanitizedLine = line.trim()
                        .replace(" ", "")
                        .replace("_", "0");
                if (line.trim().isEmpty())
                    continue;
                cells.addAll(parseLine(sanitizedLine, rowIndex));
                rowIndex++;
            }
            return cells;
        } catch (IOException e) {
            throw new UncheckedIOException("Could not parse input file " + inputFile.getName(), e);
        }
    }

    private Set<Cell> parseLine(String sanitizedLine, int rowIndex) {
        Set<Cell> cells = new HashSet<>();
        for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
            char field = sanitizedLine.charAt(columnIndex);
            CellPosition position = new CellPosition(rowIndex, columnIndex);
            Cell cell = new Cell(Character.getNumericValue(field), position);
            cells.add(cell);
        }
        return cells;
    }

    private Set<Cell> parseArray(int[][] sudoku) {
        Set<Cell> cells = new HashSet<>();
        for (int row = 0; row < sudoku.length; row++) {
            for (int column = 0; column < sudoku[0].length; column++) {
                cells.add(new Cell(sudoku[row][column], new CellPosition(row, column)));
            }
        }
        return cells;
    }
}
