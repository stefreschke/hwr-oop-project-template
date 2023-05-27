package hwr.oop.handler;

import hwr.oop.*;

public interface EditHandler {
    static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        if (args.length >= 3) {
            try {
                if (args[1].equals("edit") || args[1].equals("e")) {
                    try {
                        int index = Integer.parseInt(args[2]);
                        ToDoItem item;
                        try {
                            item = toDoList.getItems()[index];
                        } catch (Exception e) {
                            index = cui.handleBadIndex("Please enter the index of the task you want to edit.");
                            if (index == -1) return;
                            else item = toDoList.getItems()[index];
                        }
                        cui.say("Editing task at index " + index + ":");
                        cui.say(item.toString());
                        String title = cui.getTitleForEdit(item);
                        String description = cui.getDescriptionForEdit(item);
                        Priority priority = cui.getPriorityForEdit(item);
                        String bucket = cui.getBucketForEdit(item);
                        item.setTitle(title);
                        item.setDescription(description);
                        item.setPriority(priority);
                        item.setBucket(bucket);
                        cui.say("Task Edited Successfully!");
                    } catch (Exception e) {
                        cui.print(LogMode.ERROR, "Try gtd edit [index]");
                    }
                } else {
                    cui.print(LogMode.NONE, "Unknown command");
                }
            } catch (Exception e) {
                cui.print(LogMode.NONE, "Type gtd help to get help on commands.");
            }
        } else {
            cui.print(LogMode.NONE, "Invalid number of arguments");
        }
    }
}
