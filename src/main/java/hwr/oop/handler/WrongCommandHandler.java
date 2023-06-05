package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.ToDoList;

import java.util.Arrays;

import static hwr.oop.util.ConsoleColors.*;

public class WrongCommandHandler {
    WrongCommandHandler() {
    }
    public static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        cui.say("Here is a list of all commands:\n" +
                "gtd [command] [arguments]\n");
        for(CommandParser.CommandHandler command : CommandParser.CommandHandler.values()) {
            if (command != CommandParser.CommandHandler.WRONGCOMMAND) {
                cui.say( Arrays.toString(command.getCommands()) + PURPLE_BOLD + command.getArgString() + RESET + " - " + BLUE_BOLD + command.getDescription() + RESET);
            }
        }
    }
}
