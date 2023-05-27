package hwr.oop.handler;

import hwr.oop.*;

public interface ExistenceHandler {
    static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        if (args.length >= 2) {
            try {
                if (args[1].equals("add") || args[1].equals("a")) {
                    add(toDoList, cui);
                } else if (args[1].equals("remove") || args[1].equals("r")) {
                    int index;
                    try {
                        index = Integer.parseInt(args[2]);
                        toDoList.remove(index);
                    } catch (Exception e) {
                        index = cui.handleBadIndex("Please enter the index of the task you want to remove.");
                        if (index == -1) return;
                        else toDoList.remove(index);
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
    static void add(ToDoList toDoList, ConsoleUserInterface cui) throws ConsoleUserInterface.CouldNotReadInputException {
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
        cui.print(LogMode.SUCCESS, "Task Created Successfully!");
        toDoList.add(toDoItem);
    }
    static void remove(ConsoleUserInterface cui, ToDoList toDoList, int index) {
        int i = 0;
        while (i == 0) {
            try {
                toDoList.remove(index);
                cui.say("Task Removed Successfully!");
                i++;
            } catch (Exception e) {
                index = cui.handleBadIndex("Please enter the index of the task you want to remove.");
                if (index == -1) return;
            }
        }
    }
}
