package hwr.oop;

import java.io.*;

import static hwr.oop.Main.clear;
import static hwr.oop.Main.initiateSort;

public class ConsoleUserInterface {
    private final PrintStream out;
    private final InputStream in;

    public ConsoleUserInterface(PrintStream out, InputStream in) {
        this.out = out;
        this.in = in;
    }
    public void say(String message) {
        out.println(message);
    }
    public void error(String message) {
        out.println(ConsoleColors.RED_BOLD + message + ConsoleColors.RESET);
    }
    public void success(String message) {
        out.println(ConsoleColors.GREEN_BOLD + message + ConsoleColors.RESET);
    }
    public  int handleBadIndex(String message) {
        error("There is nothing at that index... 🥸");
        out.println("Try again? (y/n)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String input;
        try {
            input = reader.readLine();
        } catch (Exception e) {
            return handleBadIndex(message);
        }
        if (input.equals("y")) {
            out.println(message);
            try {
                return Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                return handleBadIndex(message);
            }
        } else {
            out.println("Okay, I'll leave you alone then. 👋");
            return -1;
        }
    }

    public  ToDoList welcome() throws IOException {
        String listFileName;
        String listName;
        ToDoList toDoList;

        out.println("Welcome To Getting Things Done 🚀");
        Program program = new Program();
        String[] env = program.getEnvironmentVariables();
        if (env == null) {
            out.println("Looks Like it is your first time using this program.");
            out.println("Lets set you up first.");
            out.println("Please enter a name for your list");
            out.println("> ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            listName = reader.readLine();
            out.println("Please provide a filePath to an existing .json file to Load your list from.");
            out.println("If you don't have one press enter to create specify your path.");
            out.println("> ");
            String filePath = reader.readLine();
            // if filePath is null or empty string
            if (filePath == null || filePath.trim().equals("")) {
                out.println("Please enter your a path to a file to save your list to.");
                out.println("> ");
                listFileName = reader.readLine();
                if (listFileName.contains(".")) {
                    listFileName = listFileName.substring(0, listFileName.lastIndexOf('.'));
                }
                program.setEnvironmentVariables(listFileName, listName);
                toDoList = new ToDoList(listName, listFileName);
            } else {
                // Load environment variables
                listFileName = filePath;
                if (listFileName.contains(".")) {
                    listFileName = listFileName.substring(0, listFileName.lastIndexOf('.'));
                }
                program.setEnvironmentVariables(listFileName, listName);
                toDoList = program.loadList(listFileName);
            }
        } else {
            // case where environment variables are set
            listFileName = env[0];
            if (listFileName.contains(".")) {
                listFileName = listFileName.substring(0, listFileName.lastIndexOf('.'));
            }
            listName = env[1];
            toDoList = program.loadList(listFileName);
            if (toDoList == null) {
                toDoList = new ToDoList(listName, listFileName);
            }
        }
        return toDoList;
    }
    public void help() {
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
    public  String getTitleForAdd(BufferedReader reader) {
        out.println("Please enter a title for your task");
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NO TITLE";
    }
    public  String getDescriptionForAdd(BufferedReader reader) {
        out.println("Please enter a description for your task");
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NO DESCRIPTION";
    }
    public  Priority getPriorityForAdd(BufferedReader reader) throws CouldNotReadInputException {
        out.println("Please select a priority for your task");
        out.println("1 - LOW, 2 - MEDIUM, 3 - HIGH");
        String priority = "-1";
        while (!priority.equals("1") && !priority.equals("2") && !priority.equals("3")) {
            try {
                priority = "";
                priority = reader.readLine();
            } catch (IOException e) {
                throw new CouldNotReadInputException();
            }
        }
        return Priority.fromInt(Integer.parseInt(priority));
    }
    public  String getTagForAdd(BufferedReader reader) {
        out.println("Add a Tag to group your tasks");
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NO TAG";
    }
    public void add(ToDoList list) throws CouldNotReadInputException {
        out.println("Create a new task");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String title = getTitleForAdd(reader);
        String description = getDescriptionForAdd(reader);
        Priority priority = getPriorityForAdd(reader);
        String tag = getTagForAdd(reader);
        ToDoItem toDoItem = new ToDoItem(
                title,
                description,
                tag,
                false ,
                priority,
                new Project(""));
        success("Task Created Successfully!");
        list.add(toDoItem);
    }
    public void list(ToDoList list) { // maybe redundant method
        out.println(list.getName() + ":");
        ToDoItem[] toDoItems = list.getItems();
        if (toDoItems == null || toDoItems.length == 0) {
            out.println("👀Looks Empty here... Add some tasks!");
            return;
        }
        for(ToDoItem toDoItem:toDoItems) out.println(toDoItem.toString());
    }
    public void remove(ToDoList list, int index) {
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
    public void done(ToDoList list, int index) {
        int i = 0;
        while (i == 0) {
            try {
                list.getItems()[index].setDone(true);
                i++;
            } catch (Exception e) {
                index = handleBadIndex("Please enter the index of the task you want to mark as done.");
                if (index == -1) return;
            }
        }
    }

    public  String getTitleForEdit(BufferedReader reader, ToDoItem item) throws CouldNotReadInputException {
        out.println("Enter new Title or press enter to skip");
        String title;
        try {
            title = reader.readLine();
            if (!title.equals("")) return title;
            else return item.getTitle();
        } catch (Exception e) {
            throw new CouldNotReadInputException();
        }
    }

    public  String getDescriptionForEdit(BufferedReader reader, ToDoItem item) throws CouldNotReadInputException {
        out.println("Enter new Description or press enter to skip");
        String description;
        try {
            description = reader.readLine();
            if (!description.equals("")) return description;
            else return item.getDescription();
        } catch (Exception e) {
            throw new CouldNotReadInputException();
        }
    }

    public  Priority getPriorityForEdit(BufferedReader reader, ToDoItem item) throws CouldNotReadInputException {
        out.println("Enter new Priority or press enter to skip");
        out.println("1 - LOW, 2 - MEDIUM, 3 - HIGH");
        String priority = "-1";
        while (!priority.equals("1") && !priority.equals("2") && !priority.equals("3") && !priority.equals("")) {
            try {
                priority = reader.readLine();
            } catch (IOException e) {
                throw new CouldNotReadInputException();
            }
        }
        if (!priority.equals("")) {
            int prioInt = Integer.parseInt(priority);
            return Priority.fromInt(prioInt);
        }
        return item.getPriority();
    }
    public  String getTagForEdit(BufferedReader reader, ToDoItem item) {
        out.println("Enter new Tag or press enter to skip");
        String tag;
        try {
            tag = reader.readLine();
            if (!tag.equals("")) return tag;
            else return item.getTag();
        } catch (IOException e) {
            out.println("Could not read your input... skipping");
        }
        return item.getTag();
    }
    public void edit(ToDoList list, int index) throws CouldNotReadInputException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        ToDoItem item;
        try {
            item = list.getItems()[index];
        } catch (Exception e) {
            index = handleBadIndex("Please enter the index of the task you want to edit.");
            if (index == -1) return;
            else item = list.getItems()[index];
        }
        out.println("Editing task at index " + index + ":");
        out.println(item.toString());
        String title = getTitleForEdit(reader, item);
        String description = getDescriptionForEdit(reader, item);
        Priority priority = getPriorityForEdit(reader, item);
        String tag = getTagForEdit(reader, item);
        item.setTitle(title);
        item.setDescription(description);
        item.setPriority(priority);
        item.setTag(tag);
        out.println("Task Edited Successfully!");

    }
    public void sortHelp() {
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

    public void parseCommands(Main main, ToDoList toDoList) throws IOException, CouldNotReadInputException {
        out.print("> ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
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
            } else if (commandArray[1].equalsIgnoreCase("list")) {
                list(toDoList);
            } else if (commandArray[1].equalsIgnoreCase("edit")) {
                try {
                    edit(toDoList, Integer.parseInt(commandArray[2]));
                } catch (Exception e) {
                    error("Try 'gtd edit [index]'");
                }
            } else if (commandArray[1].equalsIgnoreCase("sort")) {
                initiateSort(this, toDoList, commandArray);
            } else if (commandArray[1].equals("clear")) {
                clear(toDoList);
            } else if (commandArray[1].equals("exit")) {
                main.exit(this, toDoList);
            } else {
                error("Command not found.");
            }
        } else {
            error("Command not found.");
        }
    }

    public static class CouldNotReadInputException extends Exception {
        public CouldNotReadInputException() {
            super("Could not read your input... skipping");
        }
    }


}