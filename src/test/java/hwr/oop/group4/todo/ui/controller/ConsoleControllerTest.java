package hwr.oop.group4.todo.ui.controller;

import hwr.oop.group4.todo.commons.exceptions.TodoUiRuntimeException;
import hwr.oop.group4.todo.ui.controller.command.CommandNoArgs;
import hwr.oop.group4.todo.ui.controller.command.CommandStandard;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
        assertThat(input).contains("test input");
    }

    @Test
    void input() {
        final InputStream inputStream = createInputStreamForInput("test input");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController = new ConsoleController(outputStream, inputStream);

        final Optional<String> input = consoleController.input(List.of("pre1", "pre2", "3"));
        final String output = retrieveResultFrom(outputStream);
        assertThat(output).isEqualTo("pre1/pre2/3:> ");
        assertThat(input).contains("test input");
    }

    @Test
    void inputOption() {
        final InputStream inputStream = createInputStreamForInput("testCmd");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController = new ConsoleController(outputStream, inputStream);


        consoleController.inputOptions(List.of("pre1", "pre2"), List.of(
                new CommandNoArgs("testCmd", () -> consoleController.output("test cmd"))),
                new CommandNoArgs("wrong", () -> {}));
        final String output = retrieveResultFrom(outputStream);
        assertThat(output).isEqualTo("pre1/pre2:> test cmd");
    }

    @Test
    void inputOptionWithArguments() {
        final InputStream inputStream = createInputStreamForInput("test -a asd -b asdsad -c");
        final OutputStream outputStream = new ByteArrayOutputStream();
        final ConsoleController consoleController = new ConsoleController(outputStream, inputStream);

        consoleController.inputOptions(List.of("pre1", "pre2"), List.of(
                        new CommandStandard("test", arguments -> {
                            consoleController.output(String.valueOf(arguments.stream()
                                    .filter(arg -> arg.getName().equals("a")).findAny().get().getValue()));

                        })),
                new CommandNoArgs("wrong", () -> {}));
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

}