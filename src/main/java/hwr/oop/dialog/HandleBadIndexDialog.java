package hwr.oop.dialog;

import hwr.oop.ConsoleUserInterface.ConsoleUserInterface;
import hwr.oop.ConsoleUserInterface.LogMode;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HandleBadIndexDialog {
    private final ConsoleUserInterface cui;
    private final java.io.PrintStream out;
    private final BufferedReader reader;

    public HandleBadIndexDialog(ConsoleUserInterface cui) {
        this.cui = cui;
        this.out = cui.getOutputStream();
        this.reader = new BufferedReader(new InputStreamReader(cui.getInputStream()));
    }
    public int start(String message) {
        this.cui.print(LogMode.ERROR, "There is nothing at that index... 🥸");
        out.println("Try again? (y/n)");
        String input;
        try {
            input = this.reader.readLine();
        } catch (Exception e) {
            return start(message);
        }
        if (input.equals("y")) {
            out.println(message);
            try {
                return Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                return start(message);
            }
        } else {
            out.println("Okay, I'll leave you alone then. 👋");
            return -1;
        }
    }
}
