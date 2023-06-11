package hwr.oop;

import hwr.oop.ConsoleUserInterface.ConsoleUserInterface;
import hwr.oop.ConsoleUserInterface.LogMode;
import hwr.oop.dialog.GetCommandDialog;
import hwr.oop.handler.CommandParser;
import hwr.oop.persistence.PersistenceAdapter;

import java.io.PrintStream;

public class Main {
    private static final PrintStream out = new PrintStream(System.out);
    public static void main(String[] args) throws GetCommandDialog.CouldNotreadCommandException {
        ConsoleUserInterface cui = new ConsoleUserInterface(out, System.in);
        CommandParser commandParser = new CommandParser(cui);
        PersistenceAdapter persistenceAdapter = new PersistenceAdapter();
        ToDoList toDoList = persistenceAdapter.loadData();
        GetCommandDialog getCommandDialog = new GetCommandDialog(toDoList, cui, commandParser);
        while (true) {
            try {
                if (getCommandDialog.start() == 1) {
                    break;
                }
            } catch (CommandParser.CouldNotCallHandlerException e) {
                cui.print(LogMode.NONE, "Could not call handler");
            } catch (Exception e) {
                throw new GetCommandDialog.CouldNotreadCommandException("Could not read command");
            }
        }
    }
}
