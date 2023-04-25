package hwr.oop.group4.todo.ui.controller.menu;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EntryTest {

    @Test
    void entryToString() {
        final Entry entry = new Entry("entryName", "desc", List.of(new EntryArgument("a", "arg desc")));
        assertThat(entry.toString(">")).isEqualTo(
        ">entryName" + System.lineSeparator() +
                ">  desc" + System.lineSeparator() +
                ">  -a" + System.lineSeparator() +
                ">    arg desc" + System.lineSeparator()
        );
    }
}
