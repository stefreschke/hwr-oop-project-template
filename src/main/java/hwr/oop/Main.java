package hwr.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
public class Main {
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private static int handleBadIndex(String message) {
        System.out.println("There is nothing at that index... 🥸");
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

    private static List welcome() throws IOException {
        String LIST_FILE_NAME;
        String LIST_NAME;
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
    private static void help() {
        System.out.println("gtd [command] [arguments]");
        System.out.println("Commands:");
        System.out.println("  help                -  print this help");
        System.out.println("  add [Item Index]    -  add a new task");
        System.out.println("  remove [Item Index] -  remove a task");
        System.out.println("  done [Item Index]   -  mark a task as done");
        System.out.println("  edit [Item Index]   -  edit a task");
        System.out.println("  sort                -  sort your tasks");
        System.out.println("  clear               -  clear all tasks");
        System.out.println("  exit                -  exit the program");
    }
    private static void add(List list) {
        System.out.println("Create a new task");
        System.out.println("Please enter a title for your task");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String title = "";
        try {
            title = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Please enter a description for your task");
        String description = "";
        try {
            description = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Please enter a due date for your task");
        String dueDate = ""; // TODO: add date validation and add as attribute!!
        try {
            dueDate = reader.readLine(); // TODO: Exception Handling
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Please enter a priority for your task");
        System.out.println("1 - LOW, 2 - MEDIUM, 3 - HIGH");
        int priority = -1;
        try {
            priority = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Add a Tag to group your tasks");
        String tag = "";
        try {
            tag = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ToDoItem toDoItem = new ToDoItem(0, // TODO: add id assignment algorithm
                title,
                description,
                tag,
                false,
                priority == 1 ? Priority.LOW : priority == 2 ? Priority.MEDIUM : Priority.HIGH,
                new Project(""));
        System.out.println("Task Created Successfully!");

        list.add(toDoItem);
    }
    private static void list(List list) { // maybe redundant method
        System.out.println(list.getName() + ":");
        ToDoItem[] toDoItems = list.getListToDos();
        if (toDoItems == null || toDoItems.length == 0) {
            System.out.println("👀Looks Empty here... Add some tasks!");
            return;
        }
        for(ToDoItem toDoItem:toDoItems) System.out.println(toDoItem.toString());
    }
    private static void remove(List list, int index) {
        // Exception Handling for index out of bounds and invalid input
        int i = 0;
        while (i == 0) {
            try {
                list.remove(index); // TODO: zero or one indexing ?
                i++;
            } catch (Exception e) {
                index = handleBadIndex("Please enter the index of the task you want to remove.");
                if (index == -1) return;
            }
        }
    }
    private static void done(List list, int index) {
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

    private static void edit(List list, int index) { // TODO: add edit function
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ToDoItem item = list.getListToDos()[index];
        System.out.println("Editing task at index " + index + ":");
        System.out.println(item.toString());
        System.out.println("Enter new Title or press enter to skip");
        String title = "";
        try {
            title = reader.readLine();
            if (!title.equals("")) item.setTitle(title);
        } catch (IOException e) {
            System.out.println("Could not read your input... skipping");
        }
        System.out.println("Enter new Description or press enter to skip");
        String description = "";
        try {
            description = reader.readLine();
            if (!description.equals("")) item.setDescription(description);
        } catch (IOException e) {
            System.out.println("Could not read your input... skipping");
        }
        // System.out.println("Enter new Due Date or press enter to skip");
        // String dueDate = ""; // TODO: add date validation and add as attribute!!
        // try {
        //     dueDate = reader.readLine(); // TODO: Exception Handling
        //     if (!dueDate.equals("")) item.setDueDate(dueDate);
        // } catch (IOException e) {
        //     System.out.println("Could not read your input... skipping");
        // }
        System.out.println("Enter new Priority or press enter to skip");
        System.out.println("1 - LOW, 2 - MEDIUM, 3 - HIGH");
        int priority = -1;
        try {
            priority = Integer.parseInt(reader.readLine());
            if (priority != -1) item.setPriority(priority == 1 ? Priority.LOW : priority == 2 ? Priority.MEDIUM : Priority.HIGH);
        } catch (IOException e) {
            System.out.println("Could not read your input... skipping");
        }
        System.out.println("Enter new Tag or press enter to skip");
        String tag = "";
        try {
            tag = reader.readLine();
            if (!tag.equals("")) item.setTag(tag);
        } catch (IOException e) {
            System.out.println("Could not read your input... skipping");
        }
        System.out.println("Task Edited Successfully!");
    }
    private static void sortHelp() {
        System.out.println("gtd sort [option]");
        System.out.println("Options:");
        System.out.println("  priority - sort by priority");
        System.out.println("  createdAt- sort by creation date");
        System.out.println("  dueDate  - sort by due date"); // TODO
        System.out.println("  tag [tag]- sort by tag");
        System.out.println("  title    - sort by title"); // TODO
        System.out.println("  done     - sort by done"); // TODO
        System.out.println("  help     - print this help");
    }

    private static void clear(List list) {
        list.setListToDos(null);
    }
    private static void exit(List list) {
        System.out.println("exiting...");
        list.writeToJSON(list.getFileName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            assert true; // do nothing
        }
    }

    public static void main(String[] args) throws IOException {
        List toDoList = welcome();
        int i = 1;
        while (i != 0) {
            clearScreen();
            list(toDoList);
            System.out.println("Please enter a command or type 'gtd help' for more information");
            System.out.print("> ");
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
                    edit(toDoList, Integer.parseInt(commandArray[2]));
                } else if (commandArray[1].equalsIgnoreCase("sort")) {
                    try {
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
                            toDoList.bubbleUpTag(commandArray[3]);
                        } else if (commandArray[2].equalsIgnoreCase("help")) {
                            sortHelp();
                        } else sortHelp();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        sortHelp();
                    }
                } else if (commandArray[1].equals("clear")) {
                    clear(toDoList);
                } else if (commandArray[1].equals("exit")) {
                    exit(toDoList);
                    i = 0;
                } else {
                    help(); // TODO: Always print help ?
                }
            } else {
                help(); // TODO: Always print help ?
            }
        }
    }
}

