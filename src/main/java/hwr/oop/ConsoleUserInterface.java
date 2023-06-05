package hwr.oop;

import hwr.oop.handler.CommandParser;
import hwr.oop.util.ConsoleColors;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ConsoleUserInterface {
    private final PrintStream out;
    private final InputStream in;
    public ConsoleUserInterface(PrintStream out, InputStream in) {
        this.out = out;
        this.in = in;
    }
    public void print(LogMode mode, String message) {
        out.println(mode.getColor() + message + ConsoleColors.RESET);
    }
    public void say(String message) {
        out.println(message);
    }
    public void saveChanges(ToDoList toDoList) throws CouldNotSaveChangesException, ToDoList.FileNotFoundAndCoundNotCreateException {
        try {
            toDoList.writeToJSON(this, toDoList.getFileName());
        } catch (Exception e) {
            throw new CouldNotSaveChangesException();
        }
    }
    public int handleBadIndex(String message) {
        print(LogMode.ERROR, "There is nothing at that index... 🥸");
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
    public ToDoList welcome() throws IOException {
        String listFileName;
        String listName;
        ToDoList toDoList;

        out.println("Welcome To Getting Things Done 🚀");
        Program program = new Program();
        String[] env = program.getEnvironmentVariables("setup");
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
                program.setEnvironmentVariables(listFileName, listName, "setup");
                toDoList = new ToDoList(listName, listFileName);
            } else {
                // Load environment variables
                listFileName = filePath;
                if (listFileName.contains(".")) {
                    listFileName = listFileName.substring(0, listFileName.lastIndexOf('.'));
                }
                program.setEnvironmentVariables(listFileName, listName, "setup");
                toDoList = program.loadToDoList(listFileName);
            }
        } else {
            listFileName = env[0];
            listName = env[1];
            if (listFileName.contains(".")) {
                listFileName = listFileName.substring(0, listFileName.lastIndexOf('.'));
            }
            toDoList = program.loadToDoList(listFileName);
            if (toDoList == null) {
                toDoList = new ToDoList(listName, listFileName);
            }
        }
        return toDoList;
    }
    public void help() {
        out.println("gtd [command] [arguments]");
        out.println("Commands:");
        out.println("  help                            -  print this help");
        out.println("  add [Item Index]                -  add a new task");
        out.println("  remove [Item Index]             -  remove a task");
        out.println("  promote [Item Index]            -  promote a task to a further state");
        out.println("  demote [Item Index]             -  demote a task to a previous state");
        out.println("  hold [Item Index]               -  put a task on hold");
        out.println("  done [Item Index]               -  mark a task as done");
        out.println("  edit [Item Index]               -  edit a task");
        out.println("  list                            -  list all tasks");
        out.println("  sort                            -  sort your tasks");
        out.println("  createBucket [Name]             -  create a bucket for tasks");
        out.println("  showBuckets                     -  show buckets for tasks");
        out.println("  renameBucket [index] [Name]     -  changes bucket name");
        out.println("  clear                           -  clear all tasks");
        out.println("  exit                            -  exit the program");
    }
    public  String getTitleForAdd() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        out.println("Please enter a title for your task");
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NO TITLE";
    }
    public  String getDescriptionForAdd() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        out.println("Please enter a description for your task");
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NO DESCRIPTION";
    }
    public  Priority getPriorityForAdd() throws CouldNotReadInputException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        out.println("Please select a priority for your task");
        out.println("1 - LOW, 2 - MEDIUM, 3 - HIGH");
        String priority = "-1";
        while (!priority.equals("1") && !priority.equals("2") && !priority.equals("3")) {
            try {
                priority = reader.readLine();
            } catch (IOException e) {
                throw new CouldNotReadInputException();
            }
        }
        return Priority.fromInt(Integer.parseInt(priority));
    }
    public  String getBucketForAdd() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        out.println("Add a Bucket to group your tasks");
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NO BUCKET";
    }
    public void list(ToDoList toDoList) {
        out.println(toDoList.getName() + ":");
        List<ToDoItem> toDoItems = toDoList.getItems();
        if (toDoItems == null || toDoItems.isEmpty()) {
            out.println("👀Looks Empty here... Add some tasks!");
            return;
        }
        for(ToDoItem toDoItem:toDoItems) out.println(toDoItem.toString());
    }
    public void remove(ToDoList toDoList, int index) {
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
    }
    public  String getTitleForEdit(ToDoItem item) throws CouldNotReadInputException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
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

    public  String getDescriptionForEdit(ToDoItem item) throws CouldNotReadInputException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
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

    public  Priority getPriorityForEdit(ToDoItem item) throws CouldNotReadInputException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
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
    public  String getBucketForEdit(ToDoItem item) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        out.println("Enter new Bucket or press enter to skip");
        String bucket;
        try {
            bucket = reader.readLine();
            if (!bucket.equals("")) return bucket;
            else return item.getBucket();
        } catch (IOException e) {
            out.println("Could not read your input... skipping");
        }
        return item.getBucket();
    }
    public void sortHelp() {
        out.println("gtd sort [option]");
        out.println("Options:");
        out.println("  priority - sort by priority");
        out.println("  createdAt- sort by creation date");
        out.println("  dueDate  - sort by due date");
        out.println("  bucket [bucket]- sort by bucket");
        out.println("  title    - sort by title");
        out.println("  done     - sort by done");
        out.println("  help     - print this help");
    }

    public void showBuckets(ToDoList toDoList){
        Set<Bucket> buckets = toDoList.getBuckets();
        if (buckets == null || buckets.isEmpty()) {
            out.println("👀Looks Empty here... Add some buckets!");
            return;
        }
        // sort bucekts by name
        List<Bucket> sortedBuckets = new ArrayList<>(buckets);
        sortedBuckets.sort(Comparator.comparing(Bucket::getBucketName));
        for (Bucket bucket : sortedBuckets) {
            out.println(bucket.toString());
        }
    }

    public int parseCommands(ToDoList toDoList, CommandParser commandParser) throws IOException, CouldNotSaveChangesException, CommandParser.CouldNotCallHandlerException {
        out.print("> ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String command = reader.readLine();
        String[] commandArray = command.split(" ");

        commandParser.handle(toDoList, commandArray);
        try {
            saveChanges(toDoList);
        } catch (CouldNotSaveChangesException | ToDoList.FileNotFoundAndCoundNotCreateException e) {
            throw new CouldNotSaveChangesException();
        }
        return 0;
    }

    public static class CouldNotReadInputException extends Exception {
        public CouldNotReadInputException() {
            super("Could not read your input... skipping");
        }
    }
    public static class CouldNotSaveChangesException extends Exception {
        public CouldNotSaveChangesException() {
            super("Could not save your progress... please specify a file or try again.");
        }
    }


}