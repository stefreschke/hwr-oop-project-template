package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.LogMode;
import hwr.oop.ToDoList;
import hwr.oop.dialog.HandleBadIndexDialog;

public class StateHandler implements HandlerCommandsInterface {
    private static final String BAD_INDEX_MESSAGE = "Please enter the index of the item you want to change the state of:";
    public StateHandler() {
    }
    @Override
    public void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        if (args.length >= 2 && args.length <= 3) {
            try {
                if (args[1].equals("done") || args[1].equals("do")) {
                    done(toDoList, cui, args);
                } else if (args[1].equals("promote") || args[1].equals("p")) {
                    promote(toDoList, cui, args);
                } else if (args[1].equals("demote") || args[1].equals("d")) {
                    demote(toDoList, cui, args);
                } else if (args[1].equalsIgnoreCase("hold") || args[1].equals("h")) {
                    hold(toDoList, cui, args);
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

    public void done(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        int i = 0;
        int index = Integer.parseInt(args[2]);
        while (i == 0) {
            try {
                toDoList.getItems().get(index).setDone();
                i++;
            } catch (Exception e) {
                index =  new HandleBadIndexDialog(cui).start(BAD_INDEX_MESSAGE);
                if (index == -1) return;
            }
        }
    }
    public void hold(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        int i = 0;
        int index = Integer.parseInt(args[2]);
        while (i == 0) {
            try {
                toDoList.getItems().get(index).hold();
                i++;
            } catch (Exception e) {
                index =  new HandleBadIndexDialog(cui).start("Please enter the index of the task you want to mark as done.");
                if (index == -1) return;
            }
        }
    }
    public void promote(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        int i = 0;
        int index = Integer.parseInt(args[2]);
        while (i == 0) {
            try {
                toDoList.getItems().get(index).promote();
                i++;
            } catch (Exception e) {
                index =  new HandleBadIndexDialog(cui).start(BAD_INDEX_MESSAGE);
                if (index == -1) return;
            }
        }
    }
    public void demote(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        int i = 0;
        int index = Integer.parseInt(args[2]);
        while (i == 0) {
            try {
                toDoList.getItems().get(index).demote();
                i++;
            } catch (Exception e) {
                index =  new HandleBadIndexDialog(cui).start(BAD_INDEX_MESSAGE);
                if (index == -1) return;
            }
        }
    }

}
