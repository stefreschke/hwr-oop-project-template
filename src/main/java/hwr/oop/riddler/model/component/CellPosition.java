package hwr.oop.riddler.model.component;

import java.util.Objects;

public record CellPosition(int row, int column) implements Comparable<CellPosition> {
    public int box() {
        return (row / 3) * 3 + (column / 3);
    }

    @Override
    public int compareTo(CellPosition o) {
        return Integer.compare(row*9+column, o.row()*9+column());
    }
}
