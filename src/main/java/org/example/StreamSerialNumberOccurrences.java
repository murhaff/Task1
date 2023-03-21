package org.example;

import java.util.Arrays;

class StreamSerialNumberOccurrences {
    public int countOccurrences(int[] array, int target) {
        return (int) Arrays.stream(array)
                .filter(num -> num == target)
                .count();
    }
}