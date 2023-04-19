package hwr.oop.group4.todo.ui;

import java.io.PrintStream;
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
}
