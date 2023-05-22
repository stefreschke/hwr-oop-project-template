package hwr.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Set;

import static hwr.oop.ConsoleColors.*;

public class Main {
    private static final PrintStream out = new PrintStream(System.out);
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
        String input;
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

    public static ToDoList welcome() throws IOException {
        String LIST_FILE_NAME;
        String LIST_NAME;
        ToDoList toDoList;

        out.println("Welcome To Getting Things Done 🚀");
        Program program = new Program();
        String[] env = program.getEnvironmentVariables();
        if (env == null) {
            out.println("Looks Like it is your first time using this program.");
            out.println("Lets set you up first.");
            out.println("Please enter a name for your toDoList");
            out.print("> ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            LIST_NAME = reader.readLine();
            out.println("Please provide a filePath to an existing .json file to Load your toDoList from.");
            out.println("If you don't have one press enter to create specify your path.");
            out.print("> ");
            String filePath = reader.readLine();
            // if filePath is null or empty string
            if (filePath == null || filePath.trim().equals("")) {
                out.println("Please enter your a path to a file to save your toDoList to.");
                out.print("> ");
                LIST_FILE_NAME = reader.readLine();
                if (LIST_FILE_NAME.contains(".")) {
                    LIST_FILE_NAME = LIST_FILE_NAME.substring(0, LIST_FILE_NAME.lastIndexOf('.'));
                }
                program.setEnvironmentVariables(LIST_FILE_NAME, LIST_NAME);
                toDoList = new ToDoList(LIST_NAME, LIST_FILE_NAME);
            } else {
                // Load environment variables
                LIST_FILE_NAME = filePath;
                if (LIST_FILE_NAME.contains(".")) {
                    LIST_FILE_NAME = LIST_FILE_NAME.substring(0, LIST_FILE_NAME.lastIndexOf('.'));
                }
                program.setEnvironmentVariables(LIST_FILE_NAME, LIST_NAME);
                toDoList = program.loadToDoList(LIST_FILE_NAME);
            }
        } else {
            // case where environment variables are set
            LIST_FILE_NAME = env[0];
            if (LIST_FILE_NAME.contains(".")) {
                LIST_FILE_NAME = LIST_FILE_NAME.substring(0, LIST_FILE_NAME.lastIndexOf('.'));
            }
            LIST_NAME = env[1];
            toDoList = program.loadToDoList(LIST_FILE_NAME);
            if (toDoList == null) {
                toDoList = new ToDoList(LIST_NAME, LIST_FILE_NAME);
            }
        }
        return toDoList;
    }
    public static void help() {
        out.println("gtd [command] [arguments]");
        out.println("Commands:");
        out.println("  help                            -  print this help");
        out.println("  add [Item Index]                -  add a new task");
        out.println("  remove [Item Index]             -  remove a task");
        out.println("  promote [Item Index]-  promote a task to a further state");
        out.println("  demote [Item Index] -  demote a task to a previous state");
        out.println("  onhold [Item Index] -  put a task on hold");
        out.println("  done [Item Index]               -  mark a task as done");
        out.println("  edit [Item Index]               -  edit a task");
        out.println("  list                -  list all tasks");
        out.println("  sort                            -  sort your tasks");
        out.println("  createBucket [bucket name]      -  create a bucket for tasks");
        out.println("  showBuckets                     -  show buckets for tasks");
        out.println("  editBuckets [index] [new name]  -  changes bucket name");
        out.println("  clear                           -  clear all tasks");
        out.println("  exit                            -  exit the program");
    }
    public static void add(ConsoleUserInterface cui, ToDoList toDoList) {
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
        out.println("Please enter a priority for your task");
        out.println(BLUE_BOLD + "1 - LOW, " + YELLOW_BOLD + "2 - MEDIUM, " + RED_BOLD + "3 - HIGH" + RESET);
        int priority = -1;
        try {
            priority = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println("Add a Bucket to group your tasks");
        String bucket = "";
        try {
            bucket = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ToDoItem toDoItem = new ToDoItem(
                title,
                description,
                bucket,
                priority == 1 ? Priority.LOW : priority == 2 ? Priority.MEDIUM : Priority.HIGH);
        success("Task Created Successfully!");
        toDoList.add(toDoItem);
        try {
            toDoList.writeToJSON(cui, toDoList.getFileName());
        } catch (Exception e) {
            out.println("Could not save your progress... please specify a file or try again.");
        }
    }
    public static void list(ToDoList toDoList) { // maybe redundant method
        out.println(toDoList.getName() + ":");
        ToDoItem[] toDoItems = toDoList.getItems();
        if (toDoItems == null || toDoItems.length == 0) {
            out.println("👀Looks Empty here... Add some tasks!");
            return;
        }
        for(ToDoItem toDoItem:toDoItems) out.println(toDoItem.toString());
    }
    public static void remove(ConsoleUserInterface cui, ToDoList toDoList, int index) {
        // Exception Handling for index out of bounds and invalid input
        int i = 0;
        while (i == 0) {
            try {
                toDoList.remove(index);
                out.println("Task Removed Successfully!");
                i++;
            } catch (Exception e) {
                index = handleBadIndex("Please enter the index of the task you want to remove.");
                if (index == -1) return;
            }
        }
                try {
            toDoList.writeToJSON(cui, toDoList.getFileName());
        } catch (Exception e) {
            out.println("Could not save your progress... please specify a file or try again.");
        }
    }
    public static void done(ConsoleUserInterface cui, ToDoList toDoList, int index) {
        int i = 0;
        while (i == 0) {
            try {
                toDoList.getItems()[index].setDone(true);
                i++;
            } catch (Exception e) {
                 index = handleBadIndex("Please enter the index of the task you want to mark as done.");
                 if (index == -1) return;
            }
        }
                try {
            toDoList.writeToJSON(cui, toDoList.getFileName());
        } catch (Exception e) {
            out.println("Could not save your progress... please specify a file or try again.");
        }
    }

    public static void edit(ConsoleUserInterface cui, ToDoList toDoList, int index) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ToDoItem item;
        try {
            item = toDoList.getItems()[index];
        } catch (Exception e) {
            index = handleBadIndex("Please enter the index of the task you want to edit.");
            if (index == -1) return;
            else item = toDoList.getItems()[index];
        }
        out.println("Editing task at index " + index + ":");
        out.println(item.toString());
        out.println("Enter new Title or press enter to skip");
        String title;
        try {
            title = reader.readLine();
            if (!title.equals("")) item.setTitle(title);
        } catch (IOException e) {
            out.println("Could not read your input... skipping");
        }
        out.println("Enter new Description or press enter to skip");
        String description;
        try {
            description = reader.readLine();
            if (!description.equals("")) item.setDescription(description);
        } catch (IOException e) {
            out.println("Could not read your input... skipping");
        }
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
        out.println("Enter new Bucket or press enter to skip");
        String bucket;
        try {
            bucket = reader.readLine();
            if (!bucket.equals("")) item.setBucket(bucket);
        } catch (IOException e) {
            out.println("Could not read your input... skipping");
        }
        out.println("Task Edited Successfully!");
                try {
            toDoList.writeToJSON(cui, toDoList.getFileName());
        } catch (Exception e) {
            out.println("Could not save your progress... please specify a file or try again.");
        }

    }
    public static void createBucket(ToDoList toDoList, String newBucket) {
        Set<Bucket> buckets = toDoList.getBuckets();
        buckets.add(new Bucket(newBucket));
    }
    public static void showBuckets(ToDoList ToDoList){
        out.println(ToDoList.getBuckets());
    }

    public static void editBucket(ToDoList ToDoList, int index, String newBucket) {
        ToDoList.editBucket(index, newBucket);
    }

    public static void sortHelp() {
        out.println("gtd sort [option]");
        out.println("Options:");
        out.println("  priority        - sort by priority");
        out.println("  createdAt       - sort by creation date");
        out.println("  dueDate         - sort by due date"); // TODO
        out.println("  bucket [bucket] - sort by bucket"); // bucket für tag eingesetzt
        out.println("  title           - sort by title"); // TODO
        out.println("  done            - sort by done"); // TODO
        out.println("  help            - print this help");
    }

    public static void handleSort(ToDoList toDoList, String[] commandArray) {
        int nCommands = commandArray.length;
        if (nCommands == 2) {
            sortHelp();
        } else if (nCommands == 3) {
            if (commandArray[2].equals("help")) {
                sortHelp();
            }
        } else if (nCommands == 4) {
            if (commandArray[2].toLowerCase().contains("prio")) {
                if (commandArray[3].equals("asc")) {
                    toDoList.sortByPriority("asc");
                } else {
                    toDoList.sortByPriority("desc");
                }
            } else if (commandArray[2].toLowerCase().contains("create")) {
                if (commandArray[3].equals("asc")) {
                    toDoList.sortByCreatedAt("asc");
                } else {
                    toDoList.sortByCreatedAt("desc");
                }
            } else if (commandArray[2].toLowerCase().contains("bucket")) {
                toDoList.bubbleUpBucket(commandArray[3]);
            } else {
                sortHelp();
            }
        }
    }
    public static void exit(ConsoleUserInterface cui, ToDoList toDoList) {
        out.println("exiting...");
        try {
            toDoList.writeToJSON(cui, toDoList.getFileName());
        } catch (Exception e) {
            out.println("Could not save your progress... please specify a file or try again.");
        }
    }

    public static void initiateSort(ConsoleUserInterface cui, ToDoList toDoList, String[] commandArray) {
        int nCommands = commandArray.length;
        if (nCommands == 2) {
            cui.sortHelp();
        } else if (nCommands == 3) {
            if (commandArray[2].equals("help")) {
                cui.sortHelp();
            }
        } else if (nCommands == 4) {
            assignSortingAlgorithm(cui, toDoList, commandArray);
        }
    }

    public static void assignSortingAlgorithm(ConsoleUserInterface cui, ToDoList toDoList, String[] commandArray) {
        if (commandArray[2].toLowerCase().contains("prio")) {
            if (commandArray[3].equals("asc")) {
                toDoList.sortByPriority("asc");
            } else {
                toDoList.sortByPriority("desc");
            }
        } else if (commandArray[2].toLowerCase().contains("create")) {
            if (commandArray[3].equals("asc")) {
                toDoList.sortByCreatedAt("asc");
            } else {
                toDoList.sortByCreatedAt("desc");
            }
        } else if (commandArray[2].toLowerCase().contains("tag")) {
            toDoList.bubbleUpBucket(commandArray[3]);
        } else {
            cui.sortHelp();
        }
    }
    public static void clear(ConsoleUserInterface cui, ToDoList toDoList) {
        toDoList.setItems(null);
    }
    public static void main(String[] args) throws IOException, ConsoleUserInterface.CouldNotReadInputException {
        Main main = new Main();
        ConsoleUserInterface cui = new ConsoleUserInterface(System.out, System.in);
        ToDoList toDoList = cui.welcome();
        cui.say(BLUE_BOLD + "Please enter a command or type 'gtd help' for more information" + RESET);
        while (true) {
            cui.parseCommands(main, toDoList);
        }
    }
}

