package hwr.oop.handler;

import hwr.oop.Bucket;
import hwr.oop.ConsoleUserInterface.ConsoleUserInterface;
import hwr.oop.ConsoleUserInterface.LogMode;
import hwr.oop.ToDoList;
import hwr.oop.dialog.HandleBadIndexDialog;

import java.util.Set;

public class BucketHandler implements HandlerCommandsInterface {
    public BucketHandler() {
    }
    @Override
    public int handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        if (args.length >= 2) {
            try {
                if (args[1].equals("showBuckets") || args[1].equals("sb")) {
                    showBuckets(toDoList, cui);
                } else if (args[1].equals("renameBucket") || args[1].equals("rnb")) {
                    renameBuckets(toDoList, args[2], args[3], cui);
                } else {
                    cui.print(LogMode.ERROR, "Unknown command");
                }
            } catch (Exception e) {
                cui.print(LogMode.WARN, "Type gtd help to get help on commands.");
            }
        } else {
            cui.print(LogMode.ERROR, "Invalid number of arguments");
        }
        return 0;
    }
    public void showBuckets(ToDoList toDoList, ConsoleUserInterface cui) {
        Set<Bucket> buckets = toDoList.getBuckets();
        if (buckets == null || buckets.isEmpty()) {
            cui.print(LogMode.NONE, "👀Looks Empty here... Add some buckets!");
            return;
        }
        for (Bucket bucket : buckets) {
            cui.print(LogMode.NONE, bucket.toString());
        }
    }
    public void renameBuckets(ToDoList toDoList, String indexString, String newName, ConsoleUserInterface cui) {
        try {
            int index = Integer.parseInt(indexString);
            toDoList.renameBucket(index, newName);
        } catch (IndexOutOfBoundsException e) {
            new HandleBadIndexDialog(cui).start("There is not Item there to rename.");
        }
    }
}
