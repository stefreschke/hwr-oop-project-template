package hwr.oop.group4.todo.ui.controller;

import hwr.oop.group4.todo.commons.exceptions.TodoUiRuntimeException;
import hwr.oop.group4.todo.ui.controller.command.Command;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConsoleControllerTest {

    private String retrieveResultFrom(OutputStream outputStream) {
        return outputStream.toString();
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    @Test
    void inputPrompt() {
        final InputStream inputStream = createInputStreamForInput("test input");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController = new ConsoleController(outputStream, inputStream);

        final Optional<String> input = consoleController.input(List.of("pre1", "pre2", "3"), "prompt");
        final String output = retrieveResultFrom(outputStream);
        assertThat(output).isEqualTo("prompt" + System.lineSeparator() + "pre1/pre2/3:> ");
        assertThat(input).hasValue("test input");
    }

    @Test
    void input() {
        final InputStream inputStream = createInputStreamForInput("test input");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController = new ConsoleController(outputStream, inputStream);

        final Optional<String> input = consoleController.input(List.of("pre1", "pre2", "3"));
        final String output = retrieveResultFrom(outputStream);
        assertThat(output).isEqualTo("pre1/pre2/3:> ");
        assertThat(input).hasValue("test input");
    }

    @Test
    void emptyInput() {
        final InputStream inputStream = createInputStreamForInput("");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController = new ConsoleController(outputStream, inputStream);

        final Optional<String> input = consoleController.input(List.of(""));
        assertThat(input).isEmpty();
    }

    @Test
    void inputOption() {
        final InputStream inputStream = createInputStreamForInput("testCmd");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController = new ConsoleController(outputStream, inputStream);

        consoleController.inputOptions(List.of("pre1", "pre2"), List.of(
                new Command("notExecuted", args -> consoleController.output("isn't executed")),
                        new Command("testCmd", args -> consoleController.output("test cmd"))),
                new Command("wrong", args -> {})
        );
        final String output = retrieveResultFrom(outputStream);
        assertThat(output).isEqualTo("pre1/pre2:> test cmd");
    }

    @Test
    void inputOptionsNoInput() {
        final InputStream inputStream = createInputStreamForInput("");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController = new ConsoleController(outputStream, inputStream);
        final List<String> prefixes = List.of("");

        assertThatThrownBy(() -> consoleController.inputOptions(prefixes, null, null))
                .isInstanceOf(TodoUiRuntimeException.class)
                .hasMessage("Input is expected")
                .hasCause(null);

    }

    @Test
    void inputOptionWithArguments() {
        final InputStream inputStream = createInputStreamForInput("test -a asd -b asdsad -c");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController = new ConsoleController(outputStream, inputStream);

        consoleController.inputOptions(List.of("pre1", "pre2"), List.of(
                        new Command("test", arguments -> {
                            consoleController.output(String.valueOf(arguments.stream()
                                    .filter(arg -> arg.name().equals("a")).findAny().get().value())
                            );
                        })),
                new Command("wrong", args -> {}));
        final String output = retrieveResultFrom(outputStream);
        assertThat(output).isEqualTo("pre1/pre2:> asd");
    }

    @Test
    void output() {
        final InputStream inputStream = createInputStreamForInput("");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController =  new ConsoleController(outputStream, inputStream);

        consoleController.output("Test Output String");
        final String output = retrieveResultFrom(outputStream);
        assertThat(output).isEqualTo("Test Output String");
    }

    @Test
    void outputLine() {
        final InputStream inputStream = createInputStreamForInput("");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController =  new ConsoleController(outputStream, inputStream);

        consoleController.outputLine("Test Output Line");
        final String output = retrieveResultFrom(outputStream);
        assertThat(output).isEqualTo("Test Output Line" + System.lineSeparator());
    }

    @Test
    void inputBoolDefaultTest() {
        final InputStream inputStream = createInputStreamForInput("");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController = new ConsoleController(outputStream, inputStream);

        final boolean returnValue = consoleController.inputBool(List.of(""), "Eingabe.", true);
        assertThat(returnValue).isTrue();
    }

    @Test
    void inputBoolNoTest() {
        final InputStream inputStream = createInputStreamForInput("dfjewoi" + System.lineSeparator() + "no" + System.lineSeparator());
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController = new ConsoleController(outputStream, inputStream);

        final boolean returnValue = consoleController.inputBool(List.of(""), "Eingabe.", true);
        assertThat(returnValue).isFalse();
    }

    @Test
    void inputBoolPromptTest() {
        final InputStream inputStream = createInputStreamForInput(System.lineSeparator());
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController = new ConsoleController(outputStream, inputStream);

        consoleController.inputBool(List.of(""), "Eingabe.", true);
        assertThat(retrieveResultFrom(outputStream)).isEqualTo("Eingabe."+ System.lineSeparator() +
                        "Answer y/Y/yes or n/N/no (leave empty for: yes): :> ");
    }

    @Test
    void inputDateDefaultTest() {
        final InputStream inputStream = createInputStreamForInput("");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController = new ConsoleController(outputStream, inputStream);

        final LocalDateTime returnValue = consoleController.inputDate(List.of(""), "Prompt.");

        LocalDateTime now = LocalDateTime.now();
        assertThat(returnValue.getYear()).isEqualTo(now.getYear());
        assertThat(returnValue.getMonth()).isEqualTo(now.getMonth());
        assertThat(returnValue.getDayOfYear()).isEqualTo(now.getDayOfYear());
    }

    @Test
    void inputDateTest() {
        final InputStream inputStream = createInputStreamForInput("1123432" + System.lineSeparator() + "12.12.1212");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController = new ConsoleController(outputStream, inputStream);

        final LocalDateTime returnValue = consoleController.inputDate(List.of(""));

        LocalDateTime value = LocalDateTime.of(1212, 12, 12, 12, 12);
        assertThat(returnValue.getYear()).isEqualTo(value.getYear());
        assertThat(returnValue.getMonth()).isEqualTo(value.getMonth());
        assertThat(returnValue.getDayOfYear()).isEqualTo(value.getDayOfYear());
    }

    @Test
    void inputDateTimeTest() {
        final InputStream inputStream = createInputStreamForInput("12.12.1212 12:12");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController = new ConsoleController(outputStream, inputStream);

        final LocalDateTime returnValue = consoleController.inputDate(List.of(""), "Prompt.");

        LocalDateTime value = LocalDateTime.of(1212, 12, 12, 12, 12);
        assertThat(returnValue.getYear()).isEqualTo(value.getYear());
        assertThat(returnValue.getMonth()).isEqualTo(value.getMonth());
        assertThat(returnValue.getDayOfYear()).isEqualTo(value.getDayOfYear());
        assertThat(returnValue.getHour()).isEqualTo(value.getHour());
        assertThat(returnValue.getMinute()).isEqualTo(value.getMinute());
    }

}
