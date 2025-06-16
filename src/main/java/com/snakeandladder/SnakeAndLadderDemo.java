package com.snakeandladder;

import java.util.List;


public class SnakeAndLadderDemo {
    public static void main(String args[]) {
        Board board = new Board("Board_1", 100);
        Match match = new Match("Match_1");

        List<Player> players = SnakeAndLadderGame.createPlayers(5);
        board.setSnakes(SnakeAndLadderGame.createSnakes());
        board.setLadders(SnakeAndLadderGame.createLadders());

        Dice dice = new Dice(1, 6);

        match.setBoard(board);
        match.setPlayerList(players);
        match.setDice(dice);

        SnakeAndLadderGame.play(match);

    }
}
