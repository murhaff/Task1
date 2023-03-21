package org.example;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class NumberOccurrences {
    private static final int _RANGE = 100000;
    public static final int _BUFFER_SIZE = 100000;
    private static final int _ARRAY_SIZE = 10000000;
    private static final int _TARGET = 50;

    public static void main(String[] args) {
        int[] array = generateArray();
        long launch = System.currentTimeMillis();
        int countSerial = new SerialNumberOccurrences().countOccurrences(array, _TARGET);
        long finish = System.currentTimeMillis();
        System.out.println("Serial count: " + countSerial + ", time: " + (finish - launch) + " ms");
        launch = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        int countParallel = pool.invoke(new ParallelNumberOccurrences(array, _TARGET));
        finish = System.currentTimeMillis();
        System.out.println("Parallel count: " + countParallel + ", time: " + (finish - launch) + " ms");
    }
    private static int[] generateArray() {
        int[] array = new int[_ARRAY_SIZE];
        Random rand = new Random();
        for (int i = 0; i < _ARRAY_SIZE; i += _BUFFER_SIZE) {
            int bufferSize = Math.min(_BUFFER_SIZE, _ARRAY_SIZE - i);
            int[] buffer = new int[bufferSize];
            for (int j = 0; j < bufferSize; j++) {
                buffer[j] = rand.nextInt(_RANGE) + 1;
            }
            System.arraycopy(buffer, 0, array, i, bufferSize);
        }
        return array;
    }
}