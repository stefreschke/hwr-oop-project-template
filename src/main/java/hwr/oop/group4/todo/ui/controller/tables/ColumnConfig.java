package hwr.oop.group4.todo.ui.controller.tables;

public class ColumnConfig {

    private final String title;
    private final int length;

    public ColumnConfig(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }
}
