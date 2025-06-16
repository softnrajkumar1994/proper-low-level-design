package com.ratelimiting;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LeakyBucketAlgorithm implements RateLimiter {

    private final AtomicInteger bucket;
    private final int capacity;
    private final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    public LeakyBucketAlgorithm(int capacity) {
        this.capacity = capacity;
        this.bucket = new AtomicInteger(0);
        this.scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

        // Leak one token every 1 second
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            if (bucket.get() > 0) {
                bucket.decrementAndGet(); // Leak 1 token
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    @Override
    public boolean tryAcquire() {
        while (true) {
            int current = bucket.get();
            if (current >= capacity) {
                return false; // Bucket is full, reject the request
            }

            // Try to increment atomically
            // Retry if another thread changed the value in between
            if (bucket.compareAndSet(current, current + 1)) {
                return true; // Request allowed
            }

            // If we reach here, another thread modified the bucket
            // Try again with the new value
        }
    }
}
