package hwr.oop.group4.todo.ui.controller;

import hwr.oop.group4.todo.commons.exceptions.TodoRuntimeException;
import hwr.oop.group4.todo.core.Tag;
import hwr.oop.group4.todo.ui.controller.command.CommandArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

public class ConsoleHelper {

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
        } catch (DateTimeParseException ignore) {
            // fall through, try next parser
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
            LocalDate localDate = LocalDate.parse(input, formatter);
            return Optional.of(LocalDateTime.of(localDate, LocalTime.MIDNIGHT));
        } catch (DateTimeParseException ignore) {
            return Optional.empty();
        }
    }

    public Integer getId(Collection<CommandArgument<String>> args, int size) {
        Optional<CommandArgument<String>> idArg = args.stream()
                .filter(arg -> arg.name().equals("id"))
                .findFirst();

        if (idArg.isEmpty()) {
            throw new TodoRuntimeException("ID Argument not found.");
        }

        if (idArg.get().value().isBlank()) {
            throw new TodoRuntimeException("ID Argument has no parameter.");
        }

        int id;
        try {
            id = Integer.parseInt(idArg.get().value());
        } catch (NumberFormatException e) {
            throw new TodoRuntimeException("ID parameter is not a valid number.");
        }

        if (id < 0 || id >= size) {
            throw new TodoRuntimeException("ID parameter is invalid.");
        }

        return id;
    }

    public String concatTagsToString(Collection<Tag> tags) {
        final StringBuilder stringBuilder = new StringBuilder();
        final Iterator<Tag> iterator =  tags.iterator();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next().name());
            if (iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

}
