package com.ticktactoe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WinningStrategy implements ResultStrategy {
    @Override
    public boolean isMatches(Match match, Symbol symbol, int row, int col) {
        Board board = match.getBoard();
        Cell[][] grid = board.getGrid();
        int size = board.getSize();

        boolean rowWin = true, colWin = true, diag1Win = true, diag2Win = true;

        for (int i = 0; i < size; i++) {
            if (grid[row][i].getSymbol() != symbol) rowWin = false;
            if (grid[i][col].getSymbol() != symbol) colWin = false;
            if (grid[i][i].getSymbol() != symbol) diag1Win = false;
            if (grid[i][size - 1 - i].getSymbol() != symbol) diag2Win = false;
        }

        return rowWin || colWin || diag1Win || diag2Win;
    }
}