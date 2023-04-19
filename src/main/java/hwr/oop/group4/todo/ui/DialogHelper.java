package hwr.oop.group4.todo.ui;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

public class DialogHelper {

    private final PrintStream out;
    private final Scanner in;

    public DialogHelper(PrintStream out, Scanner in) {
        this.out = out;
        this.in = in;
    }

    public Integer getValidIdFromUser(String prompt, int size) {
        if (size == 0) {
            return null;
        }

        Integer id;
        do {
            out.print(prompt);
            String input = in.nextLine();

            try {
                id = Integer.valueOf(input);

                if (id < 0 || id >= size) {
                    out.println("Invalid index. (Must be between (inclusive) 0 and " + (size-1) + ")");
                    id = null;
                }
            } catch (NumberFormatException e) {
                out.println("Invalid index. (Must be a number)");
                id = null;
            }
        } while (id == null);

        return id;
    }

    public String getMenuSelectionFromUser(String message, String inputPrefix, Map<String, String> options) {
        String format = "%30.30s - %s%n";
        String input;

        out.println(message);
        options.forEach((k,v) -> out.printf(format, k, v));

        do {
            out.print(inputPrefix);
            input = in.nextLine();
        } while (!options.containsKey(input));

        return input;
    }

    public boolean getYesNoFromUser(String question, boolean defaultValue) {
        String defaultValueString = (defaultValue) ? "yes" : "no";
        while (true) {
            out.println(question);
            out.print("Answer y/Y/yes or n/N/no (leave empty for: " + defaultValueString + "): ");
            String input = in.nextLine();

            if (input.equals("")) {
                return defaultValue;
            }

            input = input.toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            }
            if (input.equals("n") || input.equals("no")) {
                return false;
            }
        }
    }

    public LocalDateTime getLocalDateTimeFromUser(String question, boolean useNowAsDefault) {
        while (true) {
            out.println(question);
            if (useNowAsDefault) {
                out.println("The current date/time will be used if you leave this empty.");
            }
            out.print("Enter a date/time formatted as 'dd.mm.yyyy' or 'dd.mm.yyyy hh:mm': ");
            String input = in.nextLine();

            if (useNowAsDefault && input.equals("")) {
                return LocalDateTime.now();
            }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu HH:mm");
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException ignore) { }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
                LocalDate localDate = LocalDate.parse(input, formatter);
                return LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
            } catch (DateTimeParseException ignore) { }
        }
    }

}
