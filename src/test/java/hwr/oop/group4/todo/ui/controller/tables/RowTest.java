package hwr.oop.group4.todo.ui.controller.tables;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RowTest {

    @Test
    void rowToString() {
        final Row row = new Row("test", "2", "3", "123");
        assertThat(row.toString("%s; %s, %s... %s")).isEqualTo("test; 2, 3... 123");
    }

    @Test
    void rowFromList() {
        final Row row = new Row(List.of("123a", "asd"));
        assertThat(row.toString("%s; %s")).isEqualTo("123a; asd");
    }

}