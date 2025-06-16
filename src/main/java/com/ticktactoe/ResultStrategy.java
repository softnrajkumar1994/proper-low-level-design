package com.ticktactoe;

public interface ResultStrategy {
    boolean isMatches(Match match, Symbol symbol, int row, int col);
}