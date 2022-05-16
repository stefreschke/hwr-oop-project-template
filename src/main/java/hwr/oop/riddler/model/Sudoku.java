package hwr.oop.riddler.model;

import hwr.oop.riddler.model.component.*;
import lombok.Getter;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Sudoku {
    private final Set<Cell> cells;
    private final Map<Cell, Set<CellGroup>> cellGroups = new HashMap<>();
    @Getter
    private final int size;
    private final int boxSize;


    public Sudoku(Set<Cell> cells) {
        this.cells = cells;
        size = (int) Math.sqrt(cells.size());
        boxSize = (int) Math.sqrt(size);
        generateCellGroups();
    }

    public Sudoku(Sudoku sudoku) {
        this.cells = sudoku.cells
                .stream()
                .map(Cell::new)
                .collect(Collectors.toSet());
        this.size = sudoku.size;
        this.boxSize = sudoku.boxSize;
        generateCellGroups();
    }

    private void generateCellGroups() {
        List<CellGroup> rows = generateCellGroups(CellGroupType.ROW);
        List<CellGroup> columns = generateCellGroups(CellGroupType.COLUMN);
        List<CellGroup> boxes = generateCellGroups(CellGroupType.BOX);

        for (Cell value : cells) {
            var position = value.getPosition();

            Set<CellGroup> groups = new HashSet<>();
            groups.add(rows.get(position.row()));
            groups.add(columns.get(position.column()));
            groups.add(boxes.get(position.box()));

            cellGroups.put(value, groups);
        }
    }

    private List<CellGroup> generateCellGroups(CellGroupType type) {
        List<CellGroup> groups = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int groupIndex = i;
            var rowContent = getCells(position -> type.getIndexFunction().apply(position) == groupIndex);
            groups.add(new CellGroup(rowContent, type));
        }
        return groups;
    }

    public Set<Cell> getCells(Predicate<CellPosition> filter) {
        return cells
                .stream()
                .filter(cell -> filter.test(cell.getPosition()))
                .collect(Collectors.toSet());
    }

    public Set<Cell> getCells() {
        return new HashSet<>(cells);
    }

    public List<Cell> getUnsolvedCells() {
        return cells.stream()
                .sorted(Comparator.comparing(Cell::getPosition))
                .filter(Cell::isEmpty).toList();
    }

    public Set<CellGroup> getCellGroups() {
        return cellGroups
                .values()
                .stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    public Set<CellGroup> getCellGroups(CellGroupType type) {
        return cellGroups
                .values()
                .stream()
                .flatMap(Set::stream)
                .filter(cellGroup -> cellGroup.getType().equals(type))
                .collect(Collectors.toSet());
    }

    public Optional<Cell> getCellAt(CellPosition position) {
        return getCells(p -> p.equals(position)).stream().findAny();
    }

    public boolean isFilled() {
        return getUnsolvedCells().isEmpty();
    }
}
