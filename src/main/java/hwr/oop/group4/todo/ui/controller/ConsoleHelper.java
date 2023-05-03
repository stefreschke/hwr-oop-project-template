package hwr.oop.group4.todo.ui.controller;

import hwr.oop.group4.todo.core.Tag;
import hwr.oop.group4.todo.ui.controller.command.CommandArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Optional;

public class ConsoleHelper {

    private final ConsoleController consoleController;

    public ConsoleHelper(ConsoleController consoleController) {
        this.consoleController = consoleController;
    }

    public Optional<String> getStringParameter(Collection<CommandArgument<String>> args, String name) {
        return args.stream()
                .filter(argument -> argument.name().equals(name))
                .map(CommandArgument::value)
                .findFirst();
    }

    public Optional<LocalDateTime> parseDate(String input) {
        if (input.isBlank()) {
            return Optional.empty();
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu HH:mm");
            return Optional.of(LocalDateTime.parse(input, formatter));
        } catch (DateTimeParseException ignore) { }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
            LocalDate localDate = LocalDate.parse(input, formatter);
            return Optional.of(LocalDateTime.of(localDate, LocalTime.MIDNIGHT));
        } catch (DateTimeParseException ignore) { }

        return Optional.empty();
    }

    public Optional<Integer> getId(Collection<CommandArgument<String>> args, int size) {
        Optional<CommandArgument<String>> idArg = args.stream()
                .filter(arg -> arg.name().equals("id"))
                .findFirst();

        if (idArg.isEmpty()) {
            consoleController.outputLine("Error: ID Argument required.");
            return Optional.empty();
        }

        if (idArg.get().value().isBlank()) {
            consoleController.outputLine("Error: ID Argument requires parameter.");
            return Optional.empty();
        }

        int id;
        try {
            id = Integer.parseInt(idArg.get().value());
        } catch (NumberFormatException e) {
            consoleController.outputLine("Error: ID is not a valid number.");
            return Optional.empty();
        }

        if (id < 0 || id >= size) {
            consoleController.outputLine("Error: ID is invalid.");
            return Optional.empty();
        }

        return Optional.of(id);
    }

    public String concatTagsToString(Collection<Tag> tags) {
        StringBuilder stringBuilder = new StringBuilder();
        tags.forEach(tag -> stringBuilder.append(tag.name()));
        return stringBuilder.toString();
    }

}
