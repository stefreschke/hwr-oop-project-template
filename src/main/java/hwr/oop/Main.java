package hwr.oop;

import hwr.oop.handler.CommandParser;

import java.io.IOException;
import java.io.PrintStream;

import static hwr.oop.util.ConsoleColors.BLUE_BOLD;
import static hwr.oop.util.ConsoleColors.RESET;

public class Main {
    private static final PrintStream out = new PrintStream(System.out);
    public static void main(String[] args) throws IOException, ToDoList.FileNotFoundAndCoundNotCreateException {
        ConsoleUserInterface cui = new ConsoleUserInterface(out, System.in);
        CommandParser commandParser = new CommandParser(cui);
        ToDoList toDoList = cui.welcome();
        cui.say(BLUE_BOLD + "Please enter a command or type 'gtd help' for more information" + RESET);
        while (true) {
            try {
                if (cui.parseCommands(toDoList, commandParser) == 1) {
                    break;
                }
            } catch (ConsoleUserInterface.CouldNotSaveChangesException | CommandParser.CouldNotCallHandlerException e) {
                cui.say("Could not save changes");
            }
        }
    }
}

