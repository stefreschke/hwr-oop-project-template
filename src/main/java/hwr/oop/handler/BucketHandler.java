package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.LogMode;
import hwr.oop.ToDoList;

public class BucketHandler {
    BucketHandler() {
    }
    public void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        if (args.length >= 2) {
            try {
                if (args[1].equals("showBuckets") || args[1].equals("sb")) {
                    showBuckets(toDoList, cui);
                } else if (args[1].equals("renameBucket") || args[1].equals("rnb")) {
                    renameBuckets(toDoList, args[2], args[3]);
                } else {
                    cui.print(LogMode.ERROR, "Unknown command");
                }
            } catch (Exception e) {
                cui.print(LogMode.WARN, "Type gtd help to get help on commands.");
            }
        } else {
            cui.print(LogMode.ERROR, "Invalid number of arguments");
        }
    }
    public static void showBuckets(ToDoList toDoList, ConsoleUserInterface cui) {
        cui.showBuckets(toDoList);
    }
    public static void renameBuckets(ToDoList toDoList, String indexString, String newName) {
        try {
            int index = Integer.parseInt(indexString);
            toDoList.renameBucket(index, newName);
            //toDoList.sortBuckets();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
