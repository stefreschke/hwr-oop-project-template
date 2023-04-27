package hwr.oop.todo;

import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class IOController {

    Scanner scanner;

    public IOController (InputStream in){
        this.scanner = new Scanner(in);
    }
    public IOController () { this.scanner = new Scanner(System.in);}

    public void printPrompt(String prompt) {
        System.out.println("\033[2J");

        int textLength = prompt.length();
        StringBuilder line = new StringBuilder();
        line.append("-".repeat(Math.max(0, (int) (textLength * 1.0) + 2)));

        System.out.println("|" + line + "|");
        System.out.println("| " + prompt + " |");
        System.out.println("|" + line + "|");
        System.out.println("\u001B[32m");
        System.out.print("> ");
    }

    public void printPrompt(List<MenuOption> options) {
        System.out.println("\033[2J");

        String longest = String.valueOf(options.stream().map(MenuOption::getDescription).max(Comparator.comparingInt(String::length)));

        StringBuilder line = new StringBuilder();
        line.append("-".repeat(Math.max(0, (int) (longest.length() * 1.0) + 4)));

        System.out.println("|" + line + "|");
        for (MenuOption option : options) {
            String description = option.getDescription();
            System.out.println("|•" + description + " ".repeat(Math.max(0, (int) (longest.length() * 1.0) + 2 - description.length())) + " |");
        }
        System.out.println("|" + line + "|");
        System.out.println("\u001B[32m");
        System.out.print("> ");
    }

    public String getInputString(){
        return this.scanner.nextLine();
    }

    public int getInputInt(){
        return this.scanner.nextInt();
    }

}