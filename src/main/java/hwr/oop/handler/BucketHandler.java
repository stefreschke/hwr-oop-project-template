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
                    Set<Bucket> buckets = toDoList.getBuckets();
                    try {
                        buckets.add(new Bucket(args[2]));
                        System.out.println("HI");
                    } catch (Exception e) {
                        new RuntimeException(e);
                    }
                } else if (args[1].equals("showBuckets") || args[1].equals("sb")) {
                    cui.showBuckets(toDoList);
                } else if (args[1].equals("renameBuckets") || args[1].equals("rnb")) {
                    try {
                        int index = Integer.parseInt(args[2]);
                        toDoList.renameBucket(index, args[3]);
                    } catch (Exception e) {
                        new RuntimeException("Try gtd rnb [index] [new Name]");
                    }
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

    public static void createBucket() {
    }
    public static void showBuckets() {
    }
    public static void editBuckets() {
    }
}
