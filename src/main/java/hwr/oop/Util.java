package hwr.oop;

import java.util.Set;

public class Util {
    public static <T> int getIndex(Set<T> set, T value) {
        int result = 0;
        for (T entry:set) {
            if (entry.equals(value)) return result;
            result++;
        }
        return -1;
    }

    public static <T> T getElementAtIndex(Set<T> set, int index) {
        int result = 0;
        for (T entry:set) {
            if (result == index) return entry;
            result++;
        }
        return null;
    }
}
