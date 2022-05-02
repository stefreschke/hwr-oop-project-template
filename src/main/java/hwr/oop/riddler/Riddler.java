package hwr.oop.riddler;

import hwr.oop.riddler.io.FileConverter;
import hwr.oop.riddler.logic.solver.SudokuSolver;
import hwr.oop.riddler.model.Sudoku;

public class Riddler {
    public static void main(String[] args) throws IllegalArgumentException {
        var converter = new FileConverter();
        var solver = new SudokuSolver();

        String filepath = parseFilepathFromArgs(args);
        var sudoku = new Sudoku(converter.parseInputFile(filepath));

        long start = System.currentTimeMillis();

        Sudoku solvedSudoku = solver.solve(sudoku);

        System.out.printf("Solved Sudoku in %dms%n", System.currentTimeMillis() - start);
    }

    private static String parseFilepathFromArgs(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("USAGE: java hwr.oop.riddler.Riddler [filepath]");
        }
        return args[args.length - 1];
    }
}
