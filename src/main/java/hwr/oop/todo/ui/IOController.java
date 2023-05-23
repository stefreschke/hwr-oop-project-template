package hwr.oop.todo.ui;

import hwr.oop.todo.ui.menu.CantWriteException;
import hwr.oop.todo.ui.menu.MenuAction;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class IOController {

    Scanner in;
    OutputStream out;

    public IOController (InputStream in, OutputStream out){
        this.in = new Scanner(in);
        this.out = out;
    }

    private void write(String s){
        try {
            out.write(s.getBytes());
        } catch (IOException e) {
            throw new CantWriteException();
        }
    }

    private void writeLine(String s){
        write(s+System.lineSeparator());
    }

    public void printPrompt(String prompt) {
        writeLine("\033[2J");

        int textLength = prompt.length();
        StringBuilder line = new StringBuilder();
        line.append("-".repeat(Math.max(0, (int) (textLength * 1.0) + 2)));

        writeLine("|" + line + "|");
        writeLine("| " + prompt + " |");
        writeLine("|" + line + "|");
        writeLine("\u001B[32m");
        write("> ");
    }

    public void printPrompt(List<MenuAction> options) {
        writeLine("\033[2J");

        String longest = String.valueOf(options.stream().map(MenuAction::getDescription).max(Comparator.comparingInt(String::length)));

        StringBuilder line = new StringBuilder();
        line.append("-".repeat(Math.max(0, (int) (longest.length() * 1.0) + 7)));

        writeLine("|" + line + "|");
        for (MenuAction option : options) {
            String description = option.getDescription();
            writeLine("| "+ option.getKey()+ ") " + description + " ".repeat(Math.max(0, (int) (longest.length() * 1.0) + 2 - description.length())) + " |");
        }
        writeLine("|" + line + "|");
        writeLine("\u001B[32m");
        writeLine("> ");
    }


    public String getInputString(){
        return in.nextLine();
    }

    public int getInputInt(){
        return in.nextInt();
    }

}