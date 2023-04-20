package hwr.oop.group4.todo.ui.controller.menu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MenuTest {

    @Test
    void menuToTest() {
        final Menu menu = new Menu("menuName", "desc",
                List.of(new Entry("testEntry", "d"),
                        new Entry("entry", "description")));
        Assertions.assertThat(menu).hasToString(
                "\033[1m<==== menuName ====>\033[0m" + System.lineSeparator() +
                "desc" + System.lineSeparator() + System.lineSeparator() +
                "Commands: " + System.lineSeparator() +
                "  testEntry" + System.lineSeparator() +
                "    d" + System.lineSeparator() +
                "  entry" + System.lineSeparator() +
                "    description"+ System.lineSeparator());
    }
}