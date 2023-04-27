package hwr.oop.todo;

import java.io.InputStream;
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

    public String getInputString(){
        return this.scanner.nextLine();
    }

    public int getInputInt(){
        return this.scanner.nextInt();
    }

}