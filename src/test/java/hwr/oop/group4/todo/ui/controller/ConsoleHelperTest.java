package hwr.oop.group4.todo.ui.controller;

import hwr.oop.group4.todo.core.Tag;
import hwr.oop.group4.todo.ui.controller.command.CommandArgument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleHelperTest {

    private String retrieveResultFrom(OutputStream outputStream) {
        return outputStream.toString();
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    @Test
    void getStringParameterTest() {
        final InputStream inputStream = createInputStreamForInput("");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleHelper consoleHelper = new ConsoleHelper(new ConsoleController(outputStream, inputStream));

        final Collection<CommandArgument<String>> arguments = List.of(new CommandArgument<>("id", "eman"));
        final Optional<String> returnValue = consoleHelper.getStringParameter(arguments, "id");

        assertThat(returnValue).isNotEmpty().get().isEqualTo("eman");
    }

    @Test
    void getStringParameterWithInvalidNameTest() {
        final InputStream inputStream = createInputStreamForInput("");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleHelper consoleHelper = new ConsoleHelper(new ConsoleController(outputStream, inputStream));

        final Collection<CommandArgument<String>> arguments = List.of(new CommandArgument<>("name", "eman"));
        final Optional<String> returnValue = consoleHelper.getStringParameter(arguments, "id");

        assertThat(returnValue).isEmpty();
    }

    @Test
    void canParseDate() {
        final InputStream inputStream = createInputStreamForInput("");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleHelper consoleHelper = new ConsoleHelper(new ConsoleController(outputStream, inputStream));

        final Optional<LocalDateTime> returnValue = consoleHelper.parseDate("12.12.1212");

        assertThat(returnValue).isPresent();
        assertThat(returnValue.get().getYear()).isEqualTo(1212);
        assertThat(returnValue.get().getMonthValue()).isEqualTo(12);
        assertThat(returnValue.get().getDayOfMonth()).isEqualTo(12);
    }

    @Test
    void canParseDateAndTime() {
        final InputStream inputStream = createInputStreamForInput("");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleHelper consoleHelper = new ConsoleHelper(new ConsoleController(outputStream, inputStream));

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
        final InputStream inputStream = createInputStreamForInput("");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleHelper consoleHelper = new ConsoleHelper(new ConsoleController(outputStream, inputStream));

        final Optional<LocalDateTime> returnValue = consoleHelper.parseDate("");

        assertThat(returnValue).isEmpty();
    }

    @Test
    void canGetId() {
        final InputStream inputStream = createInputStreamForInput("");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleHelper consoleHelper = new ConsoleHelper(new ConsoleController(outputStream, inputStream));

        final Collection<CommandArgument<String>> arguments = List.of(new CommandArgument<>("id", "12"));
        final Optional<Integer> returnValue = consoleHelper.getId(arguments, 15);

        assertThat(returnValue).isPresent().get().isEqualTo(12);
    }

    @Test
    void cantGetIdNoArg() {
        final InputStream inputStream = createInputStreamForInput("");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleHelper consoleHelper = new ConsoleHelper(new ConsoleController(outputStream, inputStream));

        final Collection<CommandArgument<String>> arguments = List.of(new CommandArgument<>("d", "12"));
        final Optional<Integer> returnValue = consoleHelper.getId(arguments, 15);

        assertThat(returnValue).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "name", "200"})
    void cantGetIdWrongValue(String value) {
        final InputStream inputStream = createInputStreamForInput("");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleHelper consoleHelper = new ConsoleHelper(new ConsoleController(outputStream, inputStream));

        final Collection<CommandArgument<String>> arguments = List.of(new CommandArgument<>("id", value));
        final Optional<Integer> returnValue = consoleHelper.getId(arguments, 15);

        assertThat(returnValue).isEmpty();
    }

    @Test
    void canConcatTagsToString() {
        final InputStream inputStream = createInputStreamForInput("");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleHelper consoleHelper = new ConsoleHelper(new ConsoleController(outputStream, inputStream));

        final Collection<Tag> tags = List.of(new Tag("a"), new Tag("b"), new Tag("c"));
        final String returnValue = consoleHelper.concatTagsToString(tags);

        assertThat(returnValue).isEqualTo("abc");
    }

}
