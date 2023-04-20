package hwr.oop.group4.todo.ui.controller.tables;

public class ColumnConfig {

    private final String tile;
    private final int length;

    public ColumnConfig(String tile, int length) {
        this.tile = tile;
        this.length = length;
    }

    public String getTile() {
        return tile;
    }

    public int getLength() {
        return length;
    }
}
