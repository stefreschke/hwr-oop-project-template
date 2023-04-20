package hwr.oop.group4.todo.ui.controller;

import hwr.oop.group4.todo.commons.exceptions.TodoRuntimeException;
import hwr.oop.group4.todo.ui.controller.command.Argument;
import hwr.oop.group4.todo.ui.controller.command.Command;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

public class ConsoleController {

    private final PrintStream out;
    private final Scanner in;


    public ConsoleController(OutputStream out, InputStream in) {
        this.out = new PrintStream(out);
        this.in = new Scanner(in);
    }

    public void outputLine(String output) {
        out.println(output);
    }

    public void output(String output) {
        out.print(output);
    }

    public void inputOptions(List<String> prefixes, Collection<Command> options, Command wrongInput) {
        final String[] input = input(prefixes).orElseThrow(() -> new TodoRuntimeException("Input is expected"))
                .split("-");
        final Collection<Argument<?>> arguments = new ArrayList<>();
        final String commandName = input[0].trim();

        Arrays.stream(input)
                .skip(1)
                .forEachOrdered(arg -> {
                    final String[] argument = arg.split(" ", 2);
                    if (argument.length == 2) {
                        arguments.add(new Argument<>(argument[0].trim(), argument[1].trim()));
                    } else {
                        arguments.add(new Argument<>(argument[0].trim(), true));
                    }
                });

        final Optional<Command> command = options.stream()
                .filter(cmd -> cmd.getName().equals(commandName))
                .findFirst();
        command.orElseGet(() -> wrongInput).call(arguments);
    }

    public Optional<String> input(List<String> prefixes) {
        return input(prefixes, null);
    }

    public Optional<String> input(List<String> prefixes, String prompt) {
        if (prompt != null && !prompt.isBlank()) {
            out.println(prompt);
        }
        out.print(buildPrefix(prefixes));
        if (in.hasNextLine()) {
            return Optional.ofNullable(in.nextLine());
        }
        return Optional.empty();
    }

    private String buildPrefix(List<String> prefixes) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<prefixes.size()-1; i++) {
            stringBuilder.append(prefixes.get(i)).append("/");
        }
        stringBuilder.append(prefixes.get(prefixes.size()-1)).append(":> ");
        return stringBuilder.toString();
    }
}
