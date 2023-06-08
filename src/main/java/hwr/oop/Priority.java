package hwr.oop;

public enum Priority {
    LOW(0),
    MEDIUM(1),
    HIGH(2);


    private final int value;

    Priority(int value) {
        this.value = value;
    }

    public int toInt() {
        return value;
    }
    public static Priority fromInt(int value) {
        return Priority.values()[value];
    }
}


