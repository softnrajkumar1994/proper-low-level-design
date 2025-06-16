package com.ratelimiting;

public interface RateLimiter {
    public boolean tryAcquire();
}
