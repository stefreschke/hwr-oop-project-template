package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.LogMode;

public interface BucketHandler {
    public static void handleUserCommand(ConsoleUserInterface cui, String[] args) {
        if (args.length == 2) {
            if (args[1].equals("createBucket") || args[1].equals("cb")) {
                BucketHandler.createBucket();
            } else if (args[1].equals("showBuckets") || args[1].equals("sb")) {
                BucketHandler.showBuckets();
            } else if (args[1].equals("editBuckets") || args[1].equals("eb")) {
                BucketHandler.editBuckets();
            } else {
                cui.print(LogMode.ERROR, "Unknown command");
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
