package com.lrucache;

public class LRUCacheDemo {
    public static void main(String[] args) {
        LRUCache<Integer, Object> cache = new LRUCache<>(5);

        System.out.println("Adding 5 items...");
        cache.put(1, "rk");
        cache.put(2, "raj");
        cache.put(3, 300);
        cache.put(4, 400);
        cache.put(5, "kumar");
        cache.printCache();  // Expected: (1,rk) (2,raj) (3,300) (4,400) (5,kumar)

        System.out.println("\nAccessing key 1 (should become most recently used): " + cache.get(1));
        cache.printCache();  // Expected: (2,raj) (3,300) (4,400) (5,kumar) (1,rk)

        System.out.println("\nAccessing key 2 (should become most recently used): " + cache.get(2));
        cache.printCache();  // Expected: (3,300) (4,400) (5,kumar) (1,rk) (2,raj)

        System.out.println("\nAccessing key 3 (should become most recently used): " + cache.get(3));
        cache.printCache();  // Expected: (4,400) (5,kumar) (1,rk) (2,raj) (3,300)

        System.out.println("\nAdding key 6 (evicts least recently used: key 4)");
        cache.put(6, 600);
        cache.printCache();  // Expected: (5,kumar) (1,rk) (2,raj) (3,300) (6,600)

        System.out.println("\nAdding key 7 (evicts least recently used: key 5)");
        cache.put(7, 700);
        cache.printCache();  // Expected: (1,rk) (2,raj) (3,300) (6,600) (7,700)
    }
}
