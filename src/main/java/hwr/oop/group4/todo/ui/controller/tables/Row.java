package hwr.oop.group4.todo.ui.controller.tables;

import java.util.List;

public class Row {

    private final String[] content;

    public Row(String... content) {
        this.content = content;
    }

    public Row(List<String> content) {
        this.content = content.toArray(String[]::new);
    }

    public String toString(String rowFormat) {
        return String.format(rowFormat, content);
    }
}
