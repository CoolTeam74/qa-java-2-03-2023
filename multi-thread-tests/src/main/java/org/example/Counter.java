package org.example;

public class Counter {
    private int count;

    public void increment() {
        int temp = count;
        count = temp + 1;
    }

    public synchronized void incrementWithTimeout() throws InterruptedException {
        int temp = count;
        wait(500);
        count = temp + 1;
    }

    public int getCount() {
        return count;
    }
}
