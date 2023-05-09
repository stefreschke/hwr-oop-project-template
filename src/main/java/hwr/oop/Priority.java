package hwr.oop;

public enum Priority {
    LOW(0),
    MEDIUM(1),
    HIGH(2);

    Priority(int value) {
        this.value = value;
    }

    private int value;
    public int toInt() {
        return value;
    }
}


