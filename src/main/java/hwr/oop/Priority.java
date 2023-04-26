package hwr.oop;

public enum Priority {
    LOW,
    MEDIUM,
    HIGH;

    public int toInt() {
        switch (this) {
            case LOW:
                return 0;
            case MEDIUM:
                return 1;
            case HIGH:
                return 2;
        }
        return -1;
    }
}


