package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCounterSimpleTest {
    @Test
    public void testMyCounter() throws InterruptedException {
        Counter counter = new Counter();
        int threadCount = 100;
        CountDownLatch latch = new CountDownLatch(threadCount);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                counter.increment();
                latch.countDown();
            });
        }
        latch.await();
        Assertions.assertEquals(threadCount, counter.getCount());
    }
}
