package hwr.oop.todo.ui;

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
            throw new RuntimeException(e);
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
        System.out.println("\033[2J");

        String longest = String.valueOf(options.stream().map(MenuAction::getDescription).max(Comparator.comparingInt(String::length)));

        StringBuilder line = new StringBuilder();
        line.append("-".repeat(Math.max(0, (int) (longest.length() * 1.0) + 7)));

        System.out.println("|" + line + "|");
        for (MenuAction option : options) {
            String description = option.getDescription();
            System.out.println("| "+ option.getKey()+ ") " + description + " ".repeat(Math.max(0, (int) (longest.length() * 1.0) + 2 - description.length())) + " |");
        }
        System.out.println("|" + line + "|");
        System.out.println("\u001B[32m");
        System.out.print("> ");
    }


    public String getInputString(){
        return in.nextLine();
    }

    public int getInputInt(){
        return in.nextInt();
    }

}