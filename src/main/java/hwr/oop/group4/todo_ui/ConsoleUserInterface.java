package hwr.oop.group4.todo_ui;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUserInterface {

    private final PrintStream out;
    private final Scanner in;

    public ConsoleUserInterface(OutputStream out, InputStream in) {
        this.out = new PrintStream(out);
        this.in = new Scanner(in);
    }

    public void helloName() {
        out.println("Who are you?");
        String name = in.nextLine();
        out.println("Hello " + name + "!");
    }
}
