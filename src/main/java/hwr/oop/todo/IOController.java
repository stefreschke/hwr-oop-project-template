package hwr.oop.todo;

import java.io.InputStream;
import java.util.Scanner;


public class IOController {


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    Scanner scanner;

    public IOController (InputStream in){
        this.scanner = new Scanner(in);
    }
    public void printPrompt(String prompt) {
        int textLength = prompt.length();
        String line = "";
        for (int i = 0; i < (int)(textLength * 1.0) + 2; i++) {
            line += "-";
        }
        System.out.println("|" + line + "|");
        System.out.println("| " + prompt + " |");
        System.out.println("|" + line + "|");
    }

    // TODO: print question

    public String getInputString(){
        return this.scanner.nextLine();
    }

    public int getInputInt(){
        return this.scanner.nextInt();
    }

}