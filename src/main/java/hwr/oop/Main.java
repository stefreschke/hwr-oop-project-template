package hwr.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {

    private static void help() {
        System.out.println("gtd [command] [arguments]");
        System.out.println("Commands:");
        System.out.println("  help                -  print this help");
        System.out.println("  add [Item Index]    -  add a new task");
        System.out.println("  remove [Item Index] -  remove a task");
        System.out.println("  done [Item Index]   -  mark a task as done");
        System.out.println("  edit [Item Index]   -  edit a task");
        System.out.println("  clear               -  clear all tasks");
        System.out.println("  exit                -  exit the program");
    }
    private static void add() {
        System.out.println("add");
    }
    private static void list(String LIST_NAME, List list) { // maybe redundant method
        System.out.println(LIST_NAME + ":");
        ToDoItem[] toDoItems = list.getListToDos();
        if (toDoItems == null) {
            System.out.println("👀Looks Empty here... Add some tasks!");
            return;
        }
        for(ToDoItem toDoItem:toDoItems) System.out.println(toDoItem.toString());
    }
    private static void remove(int index) {
        System.out.println("remove");
    }
    private static void done(int index) {
        System.out.println("done");
    }
    private static void edit(int index) {
        System.out.println("edit");
    }
    private static void clear() {
        System.out.println("clear");
    }
    private static void exit() {

    }

    public static void main(String[] args) throws IOException {
        String LIST_FILE_NAME = "";
        String LIST_NAME = "";
        List toDoList;

        System.out.println("Welcome To Getting Things Done 🚀");
        Program program = new Program();
        String[] env = program.getEnvironmentVariables();
        if (env == null) {
            System.out.println("Looks Like it is your first time using this program.");
            System.out.println("Lets set you up first.");
            System.out.println("Please enter a name for your list");
            System.out.print("> ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            LIST_NAME = reader.readLine();
            System.out.println("Please provide a filePath to an existing .json file to Load your list from.");
            System.out.println("If you don't have one press enter to create specify your path.");
            System.out.print("> ");
            String filePath = reader.readLine();
            if (filePath.equals("")) {
                System.out.println("Please enter your a path to a file to save your list to.");
                System.out.print("> ");
                LIST_FILE_NAME = reader.readLine();
                program.setEnvironmentVariables(LIST_FILE_NAME, LIST_NAME);
                toDoList = new List();
            } else {
                // Load environment variables
                LIST_FILE_NAME = filePath;
                program.setEnvironmentVariables(LIST_FILE_NAME, LIST_NAME);
                toDoList = program.loadList(LIST_FILE_NAME);
            }
        } else {
            // case where environment variables are set
            LIST_FILE_NAME = env[0];
            LIST_NAME = env[1];
            toDoList = program.loadList(LIST_FILE_NAME);
        }
        int i = 1;
        while (i != 0) {
            list(LIST_NAME, toDoList);
            System.out.println("Please enter a command or type 'gtd help' for more information");
            System.out.print("> ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            String command = reader.readLine();
            String[] commandArray = command.split(" ");
            if (commandArray[0].equals("gtd")) {
                if (commandArray[1].equals("help")) {
                    help();
                }
                if (commandArray[1].equals("add")) {
                    add();
                }
                if (commandArray[1].equals("remove")) {
                    remove(Integer.parseInt(commandArray[2]));
                }
                if (commandArray[1].equals("done")) {
                    done(Integer.parseInt(commandArray[2]));
                }
                if (commandArray[1].equals("edit")) {
                    edit(Integer.parseInt(commandArray[2]));
                }
                if (commandArray[1].equals("clear")) {
                    clear();
                }
                if (commandArray[1].equals("exit")) {
                    exit();
                    i = 0;
                }
            }
        }
    }
}

