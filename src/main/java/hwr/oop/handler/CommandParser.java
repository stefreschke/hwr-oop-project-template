package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface.ConsoleUserInterface;
import hwr.oop.ToDoList;

import java.util.Arrays;
import java.util.EnumSet;

public class CommandParser {
    private static final String INDEX_ARG = "index";
    public enum CommandHandler {
        HELP(new String[]{"help", "h"}, new String[][]{new String[]{}}, "print this help", new HelpHandler()),
        ADD(new String[]{"add", "a"}, new String[][]{new String[]{}},"add a new task", new ExistenceHandler()),
        REMOVE(new String[]{"remove", "rm"}, new String[][]{new String[]{INDEX_ARG}}, "remove a task", new ExistenceHandler()),
        PROMOTE(new String[]{"promote", "p"}, new String[][]{new String[]{INDEX_ARG}},"promote a task to a further state", new StateHandler()),
        DEMOTE(new String[]{"demote", "d"}, new String[][]{new String[]{INDEX_ARG}},"demote a task to a previous state", new StateHandler()),
        HOLD(new String[]{"hold", "hd"}, new String[][]{new String[]{INDEX_ARG}},"put a task on hold", new StateHandler()),
        DONE(new String[]{"done", "do"}, new String[][]{new String[]{INDEX_ARG}},"mark a task as done", new StateHandler()),
        EDIT(new String[]{"edit", "e"}, new String[][]{new String[]{INDEX_ARG}},"edit a task", new EditHandler()),
        LIST(new String[]{"list", "ls"}, new String[][]{new String[]{}},"list all tasks", new ListHandler()),
        SORT(new String[]{"sort", "s"}, new String[][]{new String[]{"priority | createdAt | bucket | title | done"}, new String[]{"asc | desc"}},"sort your tasks", new SortHandler()),
        SHOWBUCKETS(new String[]{"showBuckets", "sb"}, new String[][]{new String[]{}}, "show buckets for tasks", new BucketHandler()),
        RENAMEBUCKETS(new String[]{"renameBucket", "rnb"}, new String[][]{new String[]{INDEX_ARG}}, "changes bucket name", new BucketHandler()),
        CLEAR(new String[]{"clear", "cls"}, new String[][]{new String[]{}}, "clear all tasks", new ClearHandler()),
        EXIT(new String[]{"exit", "q"}, new String[][]{new String[]{}}, "exit the program", new ExitHandler()),
        WRONGCOMMAND(new String[]{}, new String[][]{new String[]{}}, "", new HelpHandler());

        private final String[] commands;
        private final String[][] arguments;
        private final String description;
        private final HandlerCommandsInterface handlerClass;

        CommandHandler(String[] commands, String[][] arguments, String description, HandlerCommandsInterface handlerClass) {
            this.commands = commands;
            this.arguments = arguments;
            this.description = description;
            this.handlerClass = handlerClass;
        }
        public String[] getCommands() {
            return commands;
        }
        public String getArgString() {
            StringBuilder sb = new StringBuilder();
            for (String[] argument : arguments) {
                sb.append(Arrays.toString(argument)).append(" ");
            }
            if (sb.toString().equals("[] ")) {
                return "";
            }
            return sb.toString();
        }
        public String getDescription() {
            return description;
        }
        public HandlerCommandsInterface getHandlerClass() {
            return handlerClass;
        }
    }

    private final ConsoleUserInterface cui;
    final CommandHandler[] commandHandlerElements;
    public CommandParser(ConsoleUserInterface cui) {
        this.cui = cui;
        this.commandHandlerElements = CommandHandler.values();
    }
    public int handle(ToDoList toDoList, String[] args) throws CouldNotCallHandlerException {
        for (CommandHandler commandElement : EnumSet.allOf(CommandHandler.class)) {
            if (Arrays.asList(commandElement.getCommands()).contains(args[1])) {
                try {
                    return callHandler(toDoList, commandElement, args);
                } catch (Exception e) {
                    throw new CouldNotCallHandlerException();
                }
            }
        }
        try {
            callHandler(toDoList, CommandHandler.WRONGCOMMAND, args);
        } catch (Exception e) {
            throw new CouldNotCallHandlerException();
        }
        return 1;
    }
    public int callHandler(ToDoList toDoList, CommandHandler commandElement, String[] userArgs) throws CouldNotCallHandlerException {
        try {
            return commandElement.getHandlerClass().handleUserCommand(toDoList, cui, userArgs);
        } catch (Exception e) {
            throw new CouldNotCallHandlerException();
        }
    }

    public static class CouldNotCallHandlerException extends Exception {
        public CouldNotCallHandlerException() {
            super("Could not process your command. Please retry or restart the application.");
        }
    }
}
