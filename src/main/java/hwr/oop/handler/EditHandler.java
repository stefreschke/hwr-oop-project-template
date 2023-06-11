package hwr.oop.handler;

import hwr.oop.*;
import hwr.oop.ConsoleUserInterface.ConsoleUserInterface;
import hwr.oop.ConsoleUserInterface.LogMode;
import hwr.oop.dialog.EditDialog;
import hwr.oop.dialog.HandleBadIndexDialog;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EditHandler implements HandlerCommandsInterface {
    public EditHandler() {
    }
    @Override
    public int handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        if (args.length == 3 && (args[1].equals("edit") || args[1].equals("e"))) {
            try {
                editTask(toDoList, cui, args);
            } catch (ConsoleUserInterface.CouldNotReadInputException e) {
                cui.print(LogMode.ERROR, "Could not edit task... If that is what you wanted to do, try 'gtd edit <index>'");
            }
        } else {
            cui.print(LogMode.NONE, "Invalid Command.");
        }
        return 0;
    }
    public void editTask(ToDoList toDoList, ConsoleUserInterface cui, String[] args) throws ConsoleUserInterface.CouldNotReadInputException {
        Map<ToDoItem, Number> itemAndIndex = getToDoItem(toDoList, cui, args[2]);
        if (itemAndIndex.isEmpty()) {
            return;
        }
        ToDoItem item = (ToDoItem) itemAndIndex.keySet().toArray()[0];
        int index = (int) itemAndIndex.values().toArray()[0];
        if (item == null) return;
        EditDialog editDialog = new EditDialog(cui, toDoList);
        ToDoItem copyToDoItem = editDialog.start(item, index);
        item.setTitle(copyToDoItem.getTitle());
        item.setDescription(copyToDoItem.getDescription());
        item.setPriority(copyToDoItem.getPriority());
        item.setBucket(copyToDoItem.getBucket());
        item.setDueDate(copyToDoItem.getDueDate());
        editDialog.end();
    }

    public Map<ToDoItem, Number> getToDoItem(ToDoList toDoList, ConsoleUserInterface cui, String arg) {
        int index = Integer.parseInt(arg);
        Map<ToDoItem, Number> itemAndIndexMap = new HashMap<>();
        int toDoListLength = toDoList.getItems().size();
        while(index >= toDoListLength || index < 0) {
            index = new HandleBadIndexDialog(cui).start("Please enter the index of the task you want to edit.");
            if (index == -1) return Collections.emptyMap();
        }
        itemAndIndexMap.put(toDoList.getItems().get(index), index);
        return itemAndIndexMap;
    }
}
