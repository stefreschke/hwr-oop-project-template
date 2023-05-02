package hwr.oop.application.console;

public class ConsoleColors {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    private static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    private static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public String black() {
        return ANSI_BLACK;
    }

    public String red() {
        return ANSI_RED;
    }

    public String green() {
        return ANSI_GREEN;
    }

    public String yellow() {
        return ANSI_YELLOW;
    }

    public String blue() {
        return ANSI_BLUE;
    }

    public String purple() {
        return ANSI_PURPLE;
    }

    public String cyan() {
        return ANSI_CYAN;
    }

    public String white() {
        return ANSI_WHITE;
    }

    public String blackBackground() {
        return ANSI_BLACK_BACKGROUND;
    }

    public String redBackground() {
        return ANSI_RED_BACKGROUND;
    }

    public String greenBackground() {
        return ANSI_GREEN_BACKGROUND;
    }

    public String yellowBackground() {
        return ANSI_YELLOW_BACKGROUND;
    }

    public String blueBackground() {
        return ANSI_BLUE_BACKGROUND;
    }

    public String purpleBackground() {
        return ANSI_PURPLE_BACKGROUND;
    }

    public String cyanBackground() {
        return ANSI_CYAN_BACKGROUND;
    }

    public String whiteBackground() {
        return ANSI_WHITE_BACKGROUND;
    }

    public String reset() {
        return ANSI_RESET;
    }
}
