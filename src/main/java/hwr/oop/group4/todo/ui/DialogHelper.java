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

    public int getValidIdFromUser(String prompt, int maxId) {
        Integer id = null;
        do {
            out.print(prompt);
            String input = in.nextLine();

            try {
                id = Integer.valueOf(input);

                if (id < 0 || id > maxId) {
                    out.println("Invalid index. (Must be between 0 and " + maxId + ")");
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

}
