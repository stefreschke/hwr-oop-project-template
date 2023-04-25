package hwr.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {

    private static void printHelp() {
        System.out.println("gtd [command] [arguments]");
        System.out.println("Commands:");
        System.out.println("  help                -  print this help");
        System.out.println("  add [Item Index]    -  add a new task");
        System.out.println("  list                -  list all tasks");
        System.out.println("  remove [Item Index] -  remove a task");
        System.out.println("  done [Item Index]   -  mark a task as done");
        System.out.println("  edit [Item Index]   -  edit a task");
        System.out.println("  clear               -  clear all tasks");
        System.out.println("  exit                -  exit the program");
    }
    public static void main(String[] args) throws IOException {
        // while loop
        int i = 1;
        while (i != 0) {
            System.out.println("Welcome To Getting Things Done 🚀");
            System.out.println("Please enter a command or type 'gtd help' for more information");
            System.out.print("> ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            String command = reader.readLine();
            String[] commandArray = command.split(" ");
            if (commandArray[0].equals("gtd")) {
                if (commandArray[1].equals("help")) {
                    printHelp();
                }
                if (commandArray[1].equals("add")) {
                    System.out.println("add");
                }
                if (commandArray[1].equals("list")) {
                    System.out.println("list");
                }
                if (commandArray[1].equals("remove")) {
                    System.out.println("remove");
                }
                if (commandArray[1].equals("done")) {
                    System.out.println("done");
                }
                if (commandArray[1].equals("edit")) {
                    System.out.println("edit");
                }
                if (commandArray[1].equals("clear")) {
                    System.out.println("clear");
                }
                if (commandArray[1].equals("exit")) {
                    System.out.println("exit");
                    i = -1;
                }
            }
            i++;
        }
    }
}

