package hwr.oop.handler;

import hwr.oop.*;
import hwr.oop.dialog.AddDialog;
import hwr.oop.dialog.HandleBadIndexDialog;

public class ExistenceHandler {
    ExistenceHandler() {
    }
    public static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        if (args.length >= 2) {
            try {
                if (args[1].equals("add") || args[1].equals("a")) {
                    add(toDoList, cui);
                } else if (args[1].equals("remove") || args[1].equals("r")) {
                    int index;
                    index = Integer.parseInt(args[2]);
                    remove(cui, toDoList, index);
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
    public static void add(ToDoList toDoList, ConsoleUserInterface cui) throws CouldNotAddException, ConsoleUserInterface.CouldNotReadInputException {
        AddDialog addDialog = new AddDialog(cui, toDoList);
        ToDoItem toDoItem = addDialog.start();
        try {
            toDoList.add(toDoItem);
            toDoList.addBucket(new Bucket(toDoItem.getBucket().getBucketName()));
        } catch (Exception e) {
            throw new CouldNotAddException("Could not add task");
        }
        addDialog.end();
    }
    public static void remove(ConsoleUserInterface cui, ToDoList toDoList, int index) {
        int i = 0;
        while (i == 0) {
            try {
                toDoList.remove(index);
                cui.say("Task Removed Successfully!");
                i++;
            } catch (Exception e) {
                index =  new HandleBadIndexDialog(cui).start("Please enter the index of the task you want to remove.");
                if (index == -1) i++;
            }
        }
    }

    public  static class CouldNotAddException extends Exception {
        public CouldNotAddException(String message) {
            super(message);
        }
    }
}
