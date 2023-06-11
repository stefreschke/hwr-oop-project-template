package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface.ConsoleUserInterface;
import hwr.oop.ConsoleUserInterface.LogMode;
import hwr.oop.ToDoList;

import java.util.Arrays;

import static hwr.oop.util.ConsoleColors.*;
import static hwr.oop.util.ConsoleColors.RESET;

public class HelpHandler implements HandlerCommandsInterface{
    public HelpHandler() {
    }
    @Override
    public int handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args ) {
        cui.print(LogMode.NONE, "Here is a list of all commands:\n" +
                "gtd [command] [arguments]\n");
        for(CommandParser.CommandHandler command : CommandParser.CommandHandler.values()) {
            if (command != CommandParser.CommandHandler.WRONGCOMMAND) {
                cui.print(LogMode.NONE, Arrays.toString(command.getCommands()) + PURPLE_BOLD + command.getArgString() + RESET + " - " + BLUE_BOLD + command.getDescription() + RESET);
            }
        }
        return 0;
    }
}
