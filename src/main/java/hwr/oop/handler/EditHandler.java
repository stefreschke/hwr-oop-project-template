package hwr.oop.handler;

import hwr.oop.*;
import java.util.Collections;

import java.util.HashMap;
import java.util.Map;

public interface EditHandler {
    static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        if (args.length >= 3 && args[2].equals("edit") || args[2].equals("e")) {
            editTask(toDoList, cui, args);
        } else {
            cui.print(LogMode.NONE, "Invalid Command.");
        }
    }
    static void editTask(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        try {
            Map<ToDoItem, Number> itemAndIndex = getToDoItem(toDoList, cui, args[2]);
            ToDoItem item = (ToDoItem) itemAndIndex.keySet().toArray()[0];
            int index = (int) itemAndIndex.values().toArray()[0];
            if (item == null) return;
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
    }
    static Map<ToDoItem, Number> getToDoItem(ToDoList toDoList, ConsoleUserInterface cui, String arg) {
        int index = Integer.parseInt(arg);
        Map<ToDoItem, Number> itemAndIndexMap = new HashMap<>();
        try {
            itemAndIndexMap.put(toDoList.getItems().get(index), index);
            return itemAndIndexMap;
        } catch (Exception e) {
            index = cui.handleBadIndex("Please enter the index of the task you want to edit.");
            if (index == -1) return Collections.emptyMap();
            else {
                itemAndIndexMap.put(toDoList.getItems().get(index), index);
                return itemAndIndexMap;
            }
        }
    }
}
