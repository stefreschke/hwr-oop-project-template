package hwr.oop;

import hwr.oop.dialog.HandleBadIndexDialog;
import hwr.oop.util.ConsoleColors;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Set;

public class ConsoleUserInterface {
    private final PrintStream out;
    private final InputStream in;

    private final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d.MM.yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.M.yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d.M.yyyy"))

            .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yy"))
            .appendOptional(DateTimeFormatter.ofPattern("d.MM.yy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.M.yy"))
            .appendOptional(DateTimeFormatter.ofPattern("d.M.yy"))
            .toFormatter();

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
        for (Bucket bucket : buckets) {
            out.println(bucket.toString());
        }
    }
    public static class CouldNotReadInputException extends Exception {
        public CouldNotReadInputException() {
            super("Could not read your input... skipping");
        }
    }
}