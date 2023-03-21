package org.example;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

class ParallelNumberOccurrences extends RecursiveTask<Integer> {
    private final int[] array;
    private final int target;

    public ParallelNumberOccurrences(int[] array, int target) {
        this.array = array;
        this.target = target;
    }

    @Override
    protected Integer compute() {
        if (array.length <= NumberOccurrences._BUFFER_SIZE) {
            return new SerialNumberOccurrences().countOccurrences(array, target);
        } else {
            int mid = array.length / 2;
            ParallelNumberOccurrences leftTask = new ParallelNumberOccurrences(Arrays.copyOfRange(array, 0, mid), target);
            ParallelNumberOccurrences rightTask = new ParallelNumberOccurrences(Arrays.copyOfRange(array, mid, array.length), target);
            leftTask.fork();
            int rightCount = rightTask.compute();
            int leftCount = leftTask.join();
            return leftCount + rightCount;
        }
    }
}
