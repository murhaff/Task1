package org.example;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

class StreamParallelNumberOccurrences {
    public int countOccurrences(int[] array, int target) {
        AtomicInteger count = new AtomicInteger(0);
        Arrays.stream(array)
                .parallel()
                .forEach(num -> {
                    if (num == target) {
                        count.incrementAndGet();
                    }
                });
        return count.get();
    }
}
