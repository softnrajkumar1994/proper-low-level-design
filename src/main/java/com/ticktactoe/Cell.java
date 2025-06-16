package com.ticktactoe;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Cell {
    int x, y;
    private Symbol symbol;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.symbol = Symbol.EMPTY;
    }
}
