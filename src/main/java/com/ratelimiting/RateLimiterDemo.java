package com.ratelimiting;

import java.util.Random;

public class RateLimiterDemo {
    public static void main(String args[]) throws InterruptedException {
        LeakyBucketAlgorithm bucketAlgorithm = new LeakyBucketAlgorithm(10);
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(500);
            int random = new Random().nextInt( 2);
            if (random == 1) {
                System.out.println("Acquired for : " + i + " result : " + bucketAlgorithm.tryAcquire() + " " + bucketAlgorithm.bucket.get());
            } else {
                System.out.println("Acquired for : " + i + " " + bucketAlgorithm.bucket.get());
            }
        }
    }
}
