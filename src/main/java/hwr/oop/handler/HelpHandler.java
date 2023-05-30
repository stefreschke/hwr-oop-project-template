package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;

public interface HelpHandler {
    static void handleUserCommand(ConsoleUserInterface cui) {
        cui.help();
    }
}
