package com.bloomfilter;

import java.util.BitSet;
import java.util.Random;
import java.util.zip.CRC32;
import java.util.zip.Adler32;

public class BloomFilter {
    private static final int SIZE = Integer.MAX_VALUE; // Size of the bit array
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int STRING_LENGTH = 10; // Length of each random string
    static Random random = new Random();
    private BitSet bitSet = new BitSet(SIZE);

    public static void main(String[] args) {
        BloomFilter bloom = new BloomFilter();
        for (int i = 0; i < 1_00_00_000; i++) {
            String str = generate();
            System.out.println(str+" "+i);
            bloom.add(str);
        }
        bloom.add("hello");
        System.out.println(bloom.mightContain("hello"));
        System.out.println(bloom.mightContain("rajkumar"));  // true
        System.out.println(bloom.mightContain("johnsmith")); // true
        System.out.println(bloom.mightContain("unknown"));   // probably false
    }

    public static String generate() {
        StringBuilder sb = new StringBuilder(STRING_LENGTH);
        for (int j = 0; j < STRING_LENGTH; j++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();

    }

    private int hash1(String input) {
        CRC32 crc = new CRC32();
        crc.update(input.getBytes());
        return (int) (crc.getValue() % SIZE);
    }

    private int hash2(String input) {
        Adler32 adler = new Adler32();
        adler.update(input.getBytes());
        return (int) (adler.getValue() % SIZE);
    }

    public void add(String value) {
        bitSet.set(hash1(value));
        bitSet.set(hash2(value));
    }

    public boolean mightContain(String value) {
        return bitSet.get(hash1(value)) && bitSet.get(hash2(value));
    }
}
