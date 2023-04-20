package hwr.oop.group4.todo.ui.controller.tables;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TableTest {

    @Test
    void tableHead() {
        final Table table = new Table(List.of(new ColumnConfig("first", 10), new ColumnConfig("2", 2)));
        assertThat(table).hasToString(
                "| first      | 2  |" + System.lineSeparator() +
                        "===================" + System.lineSeparator());
    }

    @Test
    void tableWithRows() {
        final Table table = new Table(List.of(new ColumnConfig("id", 2), new ColumnConfig("cutOF", 2)));
        table.addRow(new Row("1", "this text is cut of"));
        table.addRow(new Row("2", "cut of text"));
        table.addRow(new Row("3", "text"));
        table.addRow(new Row("", ""));

        assertThat(table).hasToString(
                "| id | cu |" + System.lineSeparator() +
                        "===========" + System.lineSeparator() +
                        "|  1 | th |" + System.lineSeparator() +
                        "|  2 | cu |" + System.lineSeparator() +
                        "|  3 | te |" + System.lineSeparator() +
                        "|    |    |" + System.lineSeparator());
    }
}