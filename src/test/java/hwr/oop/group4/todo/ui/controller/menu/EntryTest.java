package hwr.oop.group4.todo.ui.controller.menu;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EntryTest {

    @Test
    void entryToString() {
        final Entry entry = new Entry("entryName", "desc",
                List.of(new EntryArgument("a", "arg desc")));
        assertThat(entry.toString(">")).isEqualTo(
        ">entryName" + System.lineSeparator() +
                ">  desc" + System.lineSeparator() +
                ">  -a" + System.lineSeparator() +
                ">    arg desc" + System.lineSeparator()
        );
    }

    @Test
    void entryToStringNoPadding() {
        final Entry entry = new Entry("entr", "description for entry",
                List.of(new EntryArgument("lorem", "lorem ipsum")));
        assertThat(entry).hasToString(
                "entr" + System.lineSeparator() +
                        "  description for entry" + System.lineSeparator() +
                        "  -lorem" + System.lineSeparator() +
                        "    lorem ipsum" + System.lineSeparator()
        );
    }
}
