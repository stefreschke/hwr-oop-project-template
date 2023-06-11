package hwr.oop.util;

import java.util.Set;

public class Util {
    private Util(){}
    public static <T> T getElementAtIndex(Set<T> set, int index) {
        int result = 0;
        for (T entry:set) {
            if (result == index) return entry;
            result++;
        }
        return null;
    }
}
