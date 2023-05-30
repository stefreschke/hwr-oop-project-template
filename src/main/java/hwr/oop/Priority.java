package hwr.oop;

public enum Priority {
    LOW(0),
    MEDIUM(1),
    HIGH(2);


    private int value;

    Priority(int value) {
        this.value = value;
    }

    public int toInt() {
        return value;
    }
    public static Priority fromInt(int value) {
        switch (value) {
            case 2:
                return MEDIUM;
            case 3:
                return HIGH;
            default:
                return LOW;
        }
    }
}


