package com.ticktactoe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class Board {
    private int size = 0;
    private Cell[][] grid;
    private List<Cell> emptySlots;

    public Board(int size) {
        this.size = size;
        this.grid = new Cell[size][size];
        this.emptySlots = new ArrayList<>();
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.grid[i][j] = new Cell(i, j);
                this.grid[i][j].setSymbol(Symbol.EMPTY);
                emptySlots.add(this.grid[i][j]);
            }
        }
    }
}