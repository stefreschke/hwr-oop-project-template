package hwr.oop;

import static hwr.oop.util.ConsoleColors.*;

public enum Priority {
    LOW(0, BLUE_BOLD + "LOW" + RESET),
    MEDIUM(1, YELLOW_BOLD + "MEDIUM" + RESET),
    HIGH(2,RED_BOLD + "HIGH" + RESET);


    private final int value;
    private final String coloredString;

    Priority(int value, String coloredString) {
        this.value = value;
        this.coloredString = coloredString;
    }

    public int toInt() {
        return value;
    }
    public static Priority fromInt(int value) {
        return Priority.values()[value-1];
    }
    public String getColoredString() {
        return coloredString;
    }
}


