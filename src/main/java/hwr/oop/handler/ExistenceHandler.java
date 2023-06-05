package hwr.oop.handler;

import hwr.oop.*;

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
                    try {
                        index = Integer.parseInt(args[2]);
                    } catch (Exception e) {
                        cui.print(LogMode.ERROR, "Invalid index");
                        return;
                    }
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
    public static void add(ToDoList toDoList, ConsoleUserInterface cui) throws Exception {
        cui.say("Create a new task");
        String title = cui.getTitleForAdd();
        String description = cui.getDescriptionForAdd();
        Priority priority = cui.getPriorityForAdd();
        String bucket = cui.getBucketForAdd();
        ToDoItem toDoItem = new ToDoItem(
                title,
                description,
                bucket,
                priority
        );
        try {
            toDoList.add(toDoItem);
            toDoList.addBucket(new Bucket(bucket));
        } catch (Exception e) {
            cui.print(LogMode.ERROR, "Could not add task");
            return;
        }
        cui.print(LogMode.SUCCESS, "Task Created Successfully!🎉");
    }
    public static void remove(ConsoleUserInterface cui, ToDoList toDoList, int index) {
        int i = 0;
        while (i == 0) {
            try {
                toDoList.remove(index);
                cui.say("Task Removed Successfully!");
                i++;
            } catch (Exception e) {
                index = cui.handleBadIndex("Please enter the index of the task you want to remove.");
                if (index == -1) i++;
            }
        }
    }
}
