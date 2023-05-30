package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.LogMode;
import hwr.oop.ToDoList;

import java.util.Arrays;
import java.util.EnumSet;

public class CommandParser {
    public enum CommandHandler {
        HELP(new String[]{"help", "h"}, "print this help", HelpHandler.class),
        ADD(new String[]{"add", "a"}, "add a new task", ExistenceHandler.class),
        REMOVE(new String[]{"remove", "rm"}, "remove a task", ExistenceHandler.class),
        PROMOTE(new String[]{"promote", "p"}, "promote a task to a further state", StateHandler.class),
        DEMOTE(new String[]{"demote", "d"}, "demote a task to a previous state", StateHandler.class),
        HOLD(new String[]{"hold", "hd"}, "put a task on hold", StateHandler.class),
        DONE(new String[]{"done", "do"}, "mark a task as done", StateHandler.class),
        EDIT(new String[]{"edit", "e"}, "edit a task", EditHandler.class),
        LIST(new String[]{"list", "ls"}, "list all tasks", ConsoleUserInterface.class),
        SORT(new String[]{"sort", "s"}, "sort your tasks", SortHandler.class),
        CREATEBUCKET(new String[]{"createBucket", "cb"}, "create a bucket for tasks", BucketHandler.class),
        SHOWBUCKETS(new String[]{"showBuckets", "sb"}, "show buckets for tasks", BucketHandler.class),
        RENAMEBUCKETS(new String[]{"editBuckets", "rnb"}, "changes bucket name", BucketHandler.class),
        CLEAR(new String[]{"clear", "cls"}, "clear all tasks", ClearHandler.class),
        EXIT(new String[]{"exit", "q"}, "exit the program", ExitHandler.class),
        NULL(new String[]{}, "", HelpHandler.class);

        private final String[] commands;
        private final String description;
        private final Class handlerClass;

        CommandHandler(String[] commands, String description, Class handlerClass) {
            this.commands = commands;
            this.description = description;
            this.handlerClass = handlerClass;
        }

        public String[] getCommands() {
            try {
                return commands;
            } catch (Exception e) {
                return new String[]{"help", "h"};
            }
        }
        public Class getHandlerClass() {
            return handlerClass;
        }
    }

    private final ConsoleUserInterface cui;
    private final  CommandHandler[] commandHandlerElements;
    public CommandParser(ConsoleUserInterface cui) {
        this.cui = cui;
        this.commandHandlerElements = CommandHandler.values();
    }
    public int handle(ToDoList toDoList, String[] args) {
        for (CommandHandler commandElement : EnumSet.allOf(CommandHandler.class)) {
            if (Arrays.asList(commandElement.getCommands()).contains(args[1])) {
                try {
                    callHandler(toDoList, commandElement, args);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return 0;
            }
        }
        try {
            callHandler(toDoList, CommandHandler.HELP, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    private void callHandler(ToDoList toDoList, CommandHandler commandElement, String[] userArgs) throws CouldNotCallHandlerException {
        try {
            String methodName = "handleUserCommand";
            java.lang.reflect.Method method;
            try {
                method = commandElement.getHandlerClass().getMethod(methodName, ToDoList.class, ConsoleUserInterface.class, String[].class);
                method.invoke(null, toDoList, cui, userArgs);
            } catch (SecurityException e) {
                cui.print(LogMode.ERROR, "SE We cant execute that command.");
            }
            catch (NoSuchMethodException e) {
                cui.print(LogMode.ERROR, "NSM We cant execute that command.");
            }
            try {
            } catch (IllegalArgumentException e) {
                cui.print(LogMode.ERROR, "IAE e cant execute that command.");
            }
        } catch (Exception e) {
            throw new CouldNotCallHandlerException();
        }
    }

    private static class CouldNotCallHandlerException extends Exception {
        public CouldNotCallHandlerException() {
            super("Could not process your command. Please retry or restart the application.");
        }
    }

}

/* TODO
if (commandArray[0].equalsIgnoreCase("gtd")) {
    if (commandArray.length == 1) {
        help();
    } else if (commandArray[1].equalsIgnoreCase("help")) {
        help();
    } else if (commandArray[1].equalsIgnoreCase("add")) {
        add(toDoList);
    } else if (commandArray[1].equalsIgnoreCase("remove")) {
        remove(toDoList, Integer.parseInt(commandArray[2]));
    } else if (commandArray[1].equalsIgnoreCase("done")) {
        done(toDoList, Integer.parseInt(commandArray[2]));
    } else if (commandArray[1].equalsIgnoreCase("List")) {
        list(toDoList);
    } else if (commandArray[1].equalsIgnoreCase("edit")) {
        try {
            edit(toDoList, Integer.parseInt(commandArray[2]));
        } catch (Exception e) {
            print(LogMode.ERROR, "Try 'gtd edit [index]'");
        }
    } else if (commandArray[1].equalsIgnoreCase("createBucket")) {
        try {
            Main.createBucket(toDoList, commandArray[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            print(LogMode.ERROR, "Try 'gtd createBucket [Bucket]'");
        }

    } else if (commandArray[1].equalsIgnoreCase ("showBuckets")) {
        Main.showBuckets(toDoList);
    } else if (commandArray[1].equalsIgnoreCase("editBucket")){
        try {
            Main.editBucket(toDoList, Integer.parseInt(commandArray[2]), commandArray[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            print(LogMode.ERROR, "Try 'gtd editBucket [index] [new Name]'");
        }
    } else if (commandArray[1].equalsIgnoreCase("promote")) {
        try {
            toDoList.getItems()[Integer.parseInt(commandArray[2])].promote();
        } catch (ArrayIndexOutOfBoundsException e) {
            print(LogMod e.ERROR, "Try 'gtd promote [index]'");
        }
    } else if (commandArray[1].equalsIgnoreCase("demote")) {
        try {
            toDoList.getItems()[Integer.parseInt(commandArray[2])].demote();
        } catch (ArrayIndexOutOfBoundsException e) {
            print(LogMode.ERROR, "Try 'gtd demote [index]'");
        }
    } else if (commandArray[1].equalsIgnoreCase("hold")) {
        try {
            toDoList.getItems()[Integer.parseInt(commandArray[2])].hold();
        } catch (ArrayIndexOutOfBoundsException e) {
            print(LogMode.ERROR, "Try 'gtd hold [index]'");
        }
    } else if (commandArray[1].equalsIgnoreCase("sort")) {
        initiateSort(this, toDoList, commandArray);
    } else if (commandArray[1].equals("clear")) {
        clear(toDoList);
    } else if (commandArray[1].equals("exit")) {
        Main.exit(this, toDoList);
    } else {
        print(LogMode.ERROR, "Command not found.");
    }
} else {
    print(LogMode.ERROR, "Command not found.");
}
*
*/
