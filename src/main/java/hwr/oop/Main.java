package hwr.oop;

import hwr.oop.dialog.WelcomeDialog;
import hwr.oop.dialog.GetCommandDialog;
import hwr.oop.handler.CommandParser;

import java.io.PrintStream;

public class Main {
    private static final PrintStream out = new PrintStream(System.out);
    private static final String SETUP_FILE = "setup.csv";
    public static void main(String[] args) throws WelcomeDialog.CannotLaunchSetupException, GetCommandDialog.CouldNotreadCommandException {
        ConsoleUserInterface cui = new ConsoleUserInterface(out, System.in);
        CommandParser commandParser = new CommandParser(cui);
        ToDoList toDoList;
        try {
            toDoList = new WelcomeDialog(cui, SETUP_FILE, null).start();
        } catch (WelcomeDialog.CannotLaunchSetupException e) {
            throw new WelcomeDialog.CannotLaunchSetupException();
        }
        GetCommandDialog getCommandDialog = new GetCommandDialog(toDoList, cui, commandParser);
        while (true) {
            try {
                if (getCommandDialog.start() == 1) {
                    break;
                }
            } catch (CommandParser.CouldNotCallHandlerException e) {
                cui.say("Could not call handler");
            } catch (Exception e) {
                throw new GetCommandDialog.CouldNotreadCommandException("Could not read command");
            }
        }
    }
}

