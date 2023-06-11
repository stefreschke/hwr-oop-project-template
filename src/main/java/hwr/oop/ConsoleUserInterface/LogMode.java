package hwr.oop.ConsoleUserInterface;

import hwr.oop.util.ConsoleColors;

public enum LogMode {
    NONE(""),
    WARN(ConsoleColors.YELLOW_BOLD),
    ERROR(ConsoleColors.RED_BOLD),
    SUCCESS(ConsoleColors.GREEN_BOLD);

    private final String color;

    LogMode(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
