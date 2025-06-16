package com.snakeandladder;

import java.util.List;


public class SnakeAndLadderDemo {
    public static void main(String args[]) {
        Board board = new Board("B1", 100);
        Match match = new Match("M1");
        Dice dice = new Dice(1, 6);
        int numOfPlayer = 2;
        List<Player> players = SnakeAndLadderGame.generatePlayers(numOfPlayer);
        List<Snake> snakes = SnakeAndLadderGame.generateSnakes(new int[][]{{10, 7}, {98, 54}, {50, 40}, {99, 1}});
        List<Ladder> ladders = SnakeAndLadderGame.generateLadders(new int[][]{{6, 11}, {5, 54}, {17, 88}, {2, 99}});

        board.setLadders(ladders);
        board.setSnakes(snakes);
        match.setPlayerList(players);
        match.setDice(dice);
        match.setBoard(board);

        SnakeAndLadderGame.play(match);
        System.out.println("Winner : " + match.getWinner().getId());

    }
}
