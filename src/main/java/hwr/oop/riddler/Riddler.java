package hwr.oop.riddler;

import hwr.oop.riddler.io.SudokuParser;
import hwr.oop.riddler.io.SudokuPrinter;
import hwr.oop.riddler.logic.solver.BacktrackingSolver;
import hwr.oop.riddler.model.Sudoku;

import java.io.File;

public class Riddler {
    public static void main(String[] args) throws IllegalArgumentException {
        var parser = new SudokuParser();
        var solver = new BacktrackingSolver();

        String filepath = parseFilepathFromArgs(args);
        var sudoku = parser.parse(new File(filepath));

        Sudoku solvedSudoku = solver.solve(sudoku);

        // new SudokuPrinter(new FileOutputStream("")).print(sudoku);
        new SudokuPrinter(System.out).print(solvedSudoku);
    }

    private static String parseFilepathFromArgs(String[] args) {
        if (args.length != 1)
            throw new IllegalArgumentException("USAGE: java hwr.oop.riddler.Riddler [filepath]");
        return args[args.length - 1];
    }
}
