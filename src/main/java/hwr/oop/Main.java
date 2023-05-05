package hwr.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    private static final PrintStream out = new PrintStream(System.out);
    private static final Scanner in = new Scanner(System.in);
    private static final String CLEAR_SCREEN = "\033[H\033[2J";
    public static void error(String message) {
        System.out.println(ConsoleColors.RED_BOLD + message + ConsoleColors.RESET);
    }
    public static void success(String message) {
        System.out.println(ConsoleColors.GREEN_BOLD + message + ConsoleColors.RESET);
    }
    public static int handleBadIndex(String message) {
        error("There is nothing at that index... 🥸");
        System.out.println("Try again? (y/n)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = reader.readLine();
        } catch (Exception e) {
            return handleBadIndex(message);
        }
        if (input.equals("y")) {
            System.out.println(message);
            try {
                return Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                return handleBadIndex(message);
            }
        } else {
            System.out.println("Okay, I'll leave you alone then. 👋");
            return -1;
        }
    }

    public static List welcome() throws IOException {
        String LIST_FILE_NAME;
        String LIST_NAME;
        List toDoList;

        out.println("Welcome To Getting Things Done 🚀");
        Program program = new Program();
        String[] env = program.getEnvironmentVariables();
        if (env == null) {
            out.println("Looks Like it is your first time using this program.");
            out.println("Lets set you up first.");
            out.println("Please enter a name for your list");
            out.print("> ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            LIST_NAME = reader.readLine();
            out.println("Please provide a filePath to an existing .json file to Load your list from.");
            out.println("If you don't have one press enter to create specify your path.");
            out.print("> ");
            String filePath = reader.readLine();
            // if filePath is null or empty string
            if (filePath == null || filePath.trim().equals("")) {
                out.println("Please enter your a path to a file to save your list to.");
                out.print("> ");
                LIST_FILE_NAME = reader.readLine();
                if (LIST_FILE_NAME.contains(".")) {
                    LIST_FILE_NAME = LIST_FILE_NAME.substring(0, LIST_FILE_NAME.lastIndexOf('.'));
                }
                program.setEnvironmentVariables(LIST_FILE_NAME, LIST_NAME);
                toDoList = new List(LIST_NAME, LIST_FILE_NAME);
            } else {
                // Load environment variables
                LIST_FILE_NAME = filePath;
                if (LIST_FILE_NAME.contains(".")) {
                    LIST_FILE_NAME = LIST_FILE_NAME.substring(0, LIST_FILE_NAME.lastIndexOf('.'));
                }
                program.setEnvironmentVariables(LIST_FILE_NAME, LIST_NAME);
                toDoList = program.loadList(LIST_FILE_NAME);
            }
        } else {
            // case where environment variables are set
            LIST_FILE_NAME = env[0];
            if (LIST_FILE_NAME.contains(".")) {
                LIST_FILE_NAME = LIST_FILE_NAME.substring(0, LIST_FILE_NAME.lastIndexOf('.'));
            }
            LIST_NAME = env[1];
            toDoList = program.loadList(LIST_FILE_NAME);
            if (toDoList == null) {
                toDoList = new List(LIST_NAME, LIST_FILE_NAME);
            }
        }
        return toDoList;
    }
    public static void help() {
        out.println("gtd [command] [arguments]");
        out.println("Commands:");
        out.println("  help                -  print this help");
        out.println("  add [Item Index]    -  add a new task");
        out.println("  remove [Item Index] -  remove a task");
        out.println("  done [Item Index]   -  mark a task as done");
        out.println("  edit [Item Index]   -  edit a task");
        out.println("  sort                -  sort your tasks");
        out.println("  clear               -  clear all tasks");
        out.println("  exit                -  exit the program");
    }
    public static void add(List list) {
        out.println("Create a new task");
        out.println("Please enter a title for your task");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String title = "";
        try {
            title = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println("Please enter a description for your task");
        String description = "";
        try {
            description = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println("Please enter a due date for your task");
        String dueDate = ""; // TODO: add date validation and add as attribute!!
        try {
            dueDate = reader.readLine(); // TODO: Exception Handling
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println("Please enter a priority for your task");
        out.println("1 - LOW, 2 - MEDIUM, 3 - HIGH");
        int priority = -1;
        try {
            priority = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println("Add a Tag to group your tasks");
        String tag = "";
        try {
            tag = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ToDoItem toDoItem = new ToDoItem(
                0, // TODO: add id assignment algorithm
                title,
                description,
                tag,
                false ,
                priority == 1 ? Priority.LOW : priority == 2 ? Priority.MEDIUM : Priority.HIGH,
                new Project(""));
        success("Task Created Successfully!");

        list.add(toDoItem);
    }
    public static void list(List list) { // maybe redundant method
        out.println(list.getName() + ":");
        ToDoItem[] toDoItems = list.getListToDos();
        if (toDoItems == null || toDoItems.length == 0) {
            out.println("👀Looks Empty here... Add some tasks!");
            return;
        }
        for(ToDoItem toDoItem:toDoItems) out.println(toDoItem.toString());
    }
    public static void remove(List list, int index) {
        // Exception Handling for index out of bounds and invalid input
        int i = 0;
        while (i == 0) {
            try {
                list.remove(index);
                out.println("Task Removed Successfully!");
                i++;
            } catch (Exception e) {
                index = handleBadIndex("Please enter the index of the task you want to remove.");
                if (index == -1) return;
            }
        }
    }
    public static void done(List list, int index) {
        int i = 0;
        while (i == 0) {
            try {
                list.getListToDos()[index].setDone(true); // TODO: zero or one indexing ?
                i++;
            } catch (Exception e) {
                 index = handleBadIndex("Please enter the index of the task you want to mark as done.");
                 if (index == -1) return;
            }
        }
    }

    public static void edit(List list, int index) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ToDoItem item = null;
        try {
            item = list.getListToDos()[index];
        } catch (Exception e) {
            index = handleBadIndex("Please enter the index of the task you want to edit.");
            if (index == -1) return;
            else item = list.getListToDos()[index];
        }
        out.println("Editing task at index " + index + ":");
        out.println(item.toString());
        out.println("Enter new Title or press enter to skip");
        String title = "";
        try {
            title = reader.readLine();
            if (!title.equals("")) item.setTitle(title);
        } catch (IOException e) {
            out.println("Could not read your input... skipping");
        }
        out.println("Enter new Description or press enter to skip");
        String description = "";
        try {
            description = reader.readLine();
            if (!description.equals("")) item.setDescription(description);
        } catch (IOException e) {
            out.println("Could not read your input... skipping");
        }
        // System.out.println("Enter new Due Date or press enter to skip");
        // String dueDate = ""; // TODO: add date validation and add as attribute!!
        // try {
        //     dueDate = reader.readLine(); // TODO: Exception Handling
        //     if (!dueDate.equals("")) item.setDueDate(dueDate);
        // } catch (IOException e) {
        //     System.out.println("Could not read your input... skipping");
        // }
        out.println("Enter new Priority or press enter to skip");
        out.println("1 - LOW, 2 - MEDIUM, 3 - HIGH");
        String priority = "-1";
        while (!priority.equals("1") && !priority.equals("2") && !priority.equals("3") && !priority.equals("")) {
            try {
                priority = "";
                priority = reader.readLine();
            } catch (IOException e) {
                out.println("Could not read your input... skipping");
            }
        }
        if (!priority.equals(""))
            item.setPriority(priority.equals("1") ? Priority.LOW : priority.equals("2") ? Priority.MEDIUM : Priority.HIGH);
        out.println("Enter new Tag or press enter to skip");
        String tag = "";
        try {
            tag = reader.readLine();
            if (!tag.equals("")) item.setTag(tag);
        } catch (IOException e) {
            out.println("Could not read your input... skipping");
        }
        out.println("Task Edited Successfully!");
    }
    public static void sortHelp() {
        out.println("gtd sort [option]");
        out.println("Options:");
        out.println("  priority - sort by priority");
        out.println("  createdAt- sort by creation date");
        out.println("  dueDate  - sort by due date"); // TODO
        out.println("  tag [tag]- sort by tag");
        out.println("  title    - sort by title"); // TODO
        out.println("  done     - sort by done"); // TODO
        out.println("  help     - print this help");
    }

    public static void handleSort(List list, String[] commandArray) {
        int nCommands = commandArray.length;
        if (nCommands == 2) {
            sortHelp();
            return;
        } else if (nCommands == 3) {
            if (commandArray[2].equals("help")) {
                sortHelp();
                return;
            }
        } else if (nCommands == 4) {
            if (commandArray[2].toLowerCase().contains("prio")) {
                if (commandArray[3].equals("asc")) {
                    list.sortByPriority("asc");
                } else {
                    list.sortByPriority("desc");
                }
            } else if (commandArray[2].toLowerCase().contains("create")) {
                if (commandArray[3].equals("asc")) {
                    list.sortByCreatedAt("asc");
                } else {
                    list.sortByCreatedAt("desc");
                }
            } else if (commandArray[2].toLowerCase().contains("tag")) {
                list.bubbleUpTag(commandArray[3]);
            } else {
                sortHelp();
                return;
            }
        }
    }

    public static void clear(List list) {
        list.setListToDos(null);
    }
    public static void exit(List list) {
        out.println("exiting...");
        list.writeToJSON(list.getFileName());
    }

    public static void main(String[] args) throws IOException {
        List toDoList = welcome();
        int i = 1;
        while (i != 0) {
            list(toDoList);
            out.println(ConsoleColors.BLUE_BOLD + "Please enter a command or type 'gtd help' for more information" + ConsoleColors.RESET);
            out.print("> ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            String command = reader.readLine();
            String[] commandArray = command.split(" ");
            if (commandArray[0].equalsIgnoreCase("gtd")) {
                if (commandArray.length == 1) {
                    help();
                } else if (commandArray[1].equalsIgnoreCase("help")) {
                    help();
                } else if (commandArray[1].equalsIgnoreCase("add")) {
                    add(toDoList);
                } else if (commandArray[1].equalsIgnoreCase("remove")) {
                    remove(toDoList, Integer.parseInt(commandArray[2]));
                } else if (commandArray[1].equalsIgnoreCase("done")) {
                    done(toDoList, Integer.parseInt(commandArray[2]));
                } else if (commandArray[1].equalsIgnoreCase("edit")) {
                    try {
                        edit(toDoList, Integer.parseInt(commandArray[2]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        error("Try 'gtd edit [index]'");
                    }
                } else if (commandArray[1].equalsIgnoreCase("sort")) {
                    handleSort(toDoList, commandArray);
                } else if (commandArray[1].equals("clear")) {
                    clear(toDoList);
                } else if (commandArray[1].equals("exit")) {
                    exit(toDoList);
                    i = 0;
                } else {
                    error("Command not found.");
                }
            } else {
                error("Command not found.");
            }
        }
    }
}

