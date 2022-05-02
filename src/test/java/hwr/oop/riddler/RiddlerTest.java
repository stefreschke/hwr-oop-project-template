package hwr.oop.riddler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


class RiddlerTest {

    @Test
    void riddler_solvesTestSudokuFiles() {
        try {
            for (int i = 1; i <= 2; i++) {
                String filepath = String.format("src/test/resources/txt/sudoku.%d.txt", i);
                riddler_solvesSudokuFileCorrectly(filepath);
            }
        } catch (Exception e) {
            fail();
        }

    }

    private void riddler_solvesSudokuFileCorrectly(String filePath) {
        String[] args = { filePath };
        Riddler.main(args);
    }

    @Test
    void riddler_isFastEnough() {
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 20; i++) {
            Riddler.main(new String[]{String.format("src/test/resources/txt/sudoku.%d.txt", i)});
        }
        long speed = System.currentTimeMillis() - start;
        assertThat(speed).isLessThan(20000);
    }

    @Test
    void riddler_benchmark() {
        int benchmarkSampleSize = 20;
        long start = System.currentTimeMillis();
        for (int i = 1; i <= benchmarkSampleSize; i++) {
            Riddler.main(new String[]{String.format("src/test/resources/txt/sudoku.%d.txt", i)});
        }
        System.out.printf("Riddler took %dms for %d Sudokus%n",System.currentTimeMillis() - start, benchmarkSampleSize);
    }
}
