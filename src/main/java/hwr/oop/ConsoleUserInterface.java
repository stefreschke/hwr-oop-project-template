package hwr.oop;

import hwr.oop.dialog.HandleBadIndexDialog;
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
    public InputStream getInputStream() {
        return in;
    }
    public PrintStream getOutputStream() {
        return out;
    }
    public void print(LogMode mode, String message) {
        out.println(mode.getColor() + message + ConsoleColors.RESET);
    }
    public void say(String message) {
        out.println(message);
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
                index = new HandleBadIndexDialog(this).start("Please enter the index of the task you want to remove.");
                if (index == -1) return;
            }
        }
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
        List<Bucket> sortedBuckets = new ArrayList<>(buckets);
        sortedBuckets.sort(Comparator.comparing(Bucket::getBucketName));
        for (Bucket bucket : sortedBuckets) {
            out.println(bucket.toString());
        }
    }
    public static class CouldNotReadInputException extends Exception {
        public CouldNotReadInputException() {
            super("Could not read your input... skipping");
        }
    }
}