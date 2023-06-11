package hwr.oop.ConsoleUserInterface;

import hwr.oop.util.ConsoleColors;

import java.io.InputStream;
import java.io.PrintStream;

public class ConsoleUserInterface {
    private final PrintStream out;
    private final InputStream in;
  
    public ConsoleUserInterface(PrintStream out, InputStream in) {
        this.out = out;
        this.in = in;
    }
    public InputStream getInputStream() {
        return in;
    }
    public PrintStream getOutputStream() {
        return out;
    }
    public void print(LogMode mode, String message) {
        out.println(mode == LogMode.NONE ? message : mode.getColor() + message + ConsoleColors.RESET);
    }
    public static class CouldNotReadInputException extends Exception {
        public CouldNotReadInputException() {
            super("Could not read your input... skipping");
        }
    }
}