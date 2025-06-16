package com.snakeandladder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
public class Dice {
    private int min;
    private int max;

    public Dice(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int play() {
        return new Random().nextInt(max - min + 1) + min;
    }
}