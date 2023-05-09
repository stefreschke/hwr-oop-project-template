package hwr.oop.group4.todo.ui.controller;

import hwr.oop.group4.todo.commons.exceptions.TodoRuntimeException;
import hwr.oop.group4.todo.core.Tag;
import hwr.oop.group4.todo.ui.controller.command.CommandArgument;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConsoleHelperTest {

    @Test
    void getStringParameterTest() {
        final ConsoleHelper consoleHelper = new ConsoleHelper();
        final Collection<CommandArgument<String>> arguments = List.of(new CommandArgument<>("id", "eman"));

        final Optional<String> returnValue = consoleHelper.getStringParameter(arguments, "id");

        assertThat(returnValue).hasValue("eman");
    }

    @Test
    void getStringParameterWithInvalidNameTest() {
        final ConsoleHelper consoleHelper = new ConsoleHelper();
        final Collection<CommandArgument<String>> arguments = List.of(new CommandArgument<>("name", "eman"));

        final Optional<String> returnValue = consoleHelper.getStringParameter(arguments, "id");

        assertThat(returnValue).isEmpty();
    }

    @Test
    void canParseDate() {
        final ConsoleHelper consoleHelper = new ConsoleHelper();

        final Optional<LocalDateTime> returnValue = consoleHelper.parseDate("12.12.1212");

        assertThat(returnValue).isPresent();
        assertThat(returnValue.get().getYear()).isEqualTo(1212);
        assertThat(returnValue.get().getMonthValue()).isEqualTo(12);
        assertThat(returnValue.get().getDayOfMonth()).isEqualTo(12);
    }

    @Test
    void canParseDateAndTime() {
        final ConsoleHelper consoleHelper = new ConsoleHelper();

        final Optional<LocalDateTime> returnValue = consoleHelper.parseDate("12.12.1212 12:12");

        assertThat(returnValue).isPresent();
        assertThat(returnValue.get().getYear()).isEqualTo(1212);
        assertThat(returnValue.get().getMonthValue()).isEqualTo(12);
        assertThat(returnValue.get().getDayOfMonth()).isEqualTo(12);
        assertThat(returnValue.get().getHour()).isEqualTo(12);
        assertThat(returnValue.get().getMinute()).isEqualTo(12);
    }

    @Test
    void canParseEmptyDate() {
        final ConsoleHelper consoleHelper = new ConsoleHelper();

        final Optional<LocalDateTime> returnValue = consoleHelper.parseDate("");

        assertThat(returnValue).isEmpty();
    }

    @Test
    void canGetId() {
        final ConsoleHelper consoleHelper = new ConsoleHelper();
        final Collection<CommandArgument<String>> arguments = List.of(new CommandArgument<>("id", "12"));

        final int returnValue = consoleHelper.getId(arguments, 15);

        assertThat(returnValue).isEqualTo(12);
    }

    @Test
    void cantGetIdNoArg() {
        final ConsoleHelper consoleHelper = new ConsoleHelper();
        final Collection<CommandArgument<String>> arguments = List.of(new CommandArgument<>("d", "12"));

        assertThatThrownBy(() -> {
            final int returnValue = consoleHelper.getId(arguments, 15);
        }).isInstanceOf(TodoRuntimeException.class)
                .hasMessage("ID Argument not found.");
    }

    @Test
    void cantGetIdNoParameter() {
        final ConsoleHelper consoleHelper = new ConsoleHelper();
        final Collection<CommandArgument<String>> arguments = List.of(new CommandArgument<>("id", ""));

        assertThatThrownBy(() -> {
            final int returnValue = consoleHelper.getId(arguments, 15);
        }).isInstanceOf(TodoRuntimeException.class)
                .hasMessage("ID Argument has no parameter.");
    }

    @Test
    void cantGetIdNoNumber() {
        final ConsoleHelper consoleHelper = new ConsoleHelper();
        final Collection<CommandArgument<String>> arguments = List.of(new CommandArgument<>("id", "name"));

        assertThatThrownBy(() -> {
            final int returnValue = consoleHelper.getId(arguments, 15);
        }).isInstanceOf(TodoRuntimeException.class)
                .hasMessage("ID parameter is not a valid number.");
    }

    @Test
    void cantGetIdInvalidNumber() {
        final ConsoleHelper consoleHelper = new ConsoleHelper();
        final Collection<CommandArgument<String>> arguments = List.of(new CommandArgument<>("id", "21"));

        assertThatThrownBy(() -> {
            final int returnValue = consoleHelper.getId(arguments, 15);
        }).isInstanceOf(TodoRuntimeException.class)
                .hasMessage("ID parameter is invalid.");
    }

    @Test
    void canConcatTagsToString() {
        final ConsoleHelper consoleHelper = new ConsoleHelper();
        final Collection<Tag> tags = List.of(new Tag("a"), new Tag("b"), new Tag("c"));

        final String returnValue = consoleHelper.concatTagsToString(tags);

        assertThat(returnValue).isEqualTo("abc");
    }

}
