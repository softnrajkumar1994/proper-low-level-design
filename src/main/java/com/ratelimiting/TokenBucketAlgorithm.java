package com.ratelimiting;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucketAlgorithm implements RateLimiter {

    private final AtomicInteger bucket;
    private final int capacity;
    private final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    public TokenBucketAlgorithm(int capacity) {
        this.capacity = capacity;
        this.bucket = new AtomicInteger(0);
        this.scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

        // Add one token every 1 second, up to the capacity
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            while (true) {
                int current = bucket.get();
                if (current >= capacity) {
                    break; // Do not overfill
                }
                if (bucket.compareAndSet(current, current + 1)) {
                    break; // Successfully added one token
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public boolean tryAcquire() {
        while (true) {
            int current = bucket.get();
            if (current <= 0) {
                return false; // No tokens available
            }

            if (bucket.compareAndSet(current, current - 1)) {
                return true; // Successfully consumed one token
            }

            // Retry if another thread consumed in between
        }
    }
}
