package hwr.oop.handler;

import hwr.oop.Bucket;
import hwr.oop.ConsoleUserInterface;
import hwr.oop.LogMode;
import hwr.oop.ToDoList;

import java.util.Set;

public interface BucketHandler {
    static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        if (args.length >= 2) {
            try {
                if (args[1].equals("createBucket") || args[1].equals("cb")) {
                    createBucket(toDoList, args[2]);
                } else if (args[1].equals("showBuckets") || args[1].equals("sb")) {
                    showBuckets(toDoList, cui);
                } else if (args[1].equals("renameBuckets") || args[1].equals("rnb")) {
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

    static void createBucket(ToDoList toDoList, String name) {
        Set<Bucket> buckets = toDoList.getBuckets();
        try {
            buckets.add(new Bucket(name));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static void showBuckets(ToDoList toDoList, ConsoleUserInterface cui) {
        cui.showBuckets(toDoList);
    }
    static void renameBuckets(ToDoList toDoList, String indexString, String newName) {
        try {
            int index = Integer.parseInt(indexString);
            toDoList.renameBucket(index, newName);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}
