package hwr.oop;

public enum EstimatedTime {
    SHORT(0),

    MEDIUM(1),

    LONG(2),

    XLONG(3);

    private final int value;

    EstimatedTime(int value) {
        this.value = value;
    }

    public int toInt() {
        return value;
    }
    public static EstimatedTime fromInt(int value) {
        if (value < 4 && value >= 0) {
            return EstimatedTime.values()[value];
        } else {
            return EstimatedTime.MEDIUM;
        }
    }
}
