package com.snakeandladder;

import java.util.ArrayList;
import java.util.List;

public class SnakeAndLadderGame {
    public static List<Player> generatePlayers(int numOfPlayers) {
        List<Player> list = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            list.add(new Player("" + i, " name_" + i));
        }
        return list;
    }

    public static List<Snake> generateSnakes(int[][] snakes) {
        List<Snake> list = new ArrayList<>();
        for (int i = 0; i < snakes.length; i++) {
            list.add(new Snake(snakes[i][0], snakes[i][1]));
        }
        return list;
    }

    public static List<Ladder> generateLadders(int[][] ladders) {
        List<Ladder> list = new ArrayList<>();
        for (int i = 0; i < ladders.length; i++) {
            list.add(new Ladder(ladders[i][0], ladders[i][1]));
        }
        return list;
    }

    public static void play(Match match) {
        match.setMatchStatus(MatchStatus.IN_PROGRESS);
        for (int i = 0; i < match.getPlayerList().size(); i++) {
            Player player = match.getPlayerList().get(i);
            match.getBoard().getPlayerPositionHashMap().put(player, -1);
        }
        while (match.getMatchStatus() != MatchStatus.ENDED) {
            for (int i = 0; i < match.getPlayerList().size(); i++) {
                if (match.getMatchStatus() == MatchStatus.ENDED) {
                    break;
                }
                Player player = match.getPlayerList().get(i);
                int currentPos = match.getBoard().getPlayerPositionHashMap().get(player);
                int nextPos = match.getDice().play();

                if (currentPos == -1 && nextPos != 1) {
                    System.out.println("Player not in board yet , he needs to put 1 to continue : " + player.getId());
                    continue;
                }
                nextPos = nextPos + currentPos;
                if (nextPos > match.getBoard().getMaxPos()) {
                    continue;
                }
                for (Snake snake : match.getBoard().getSnakes()) {
                    if (snake.getHead() == nextPos) {
                        nextPos = snake.getTail();
                        break;
                    }
                }
                for (Ladder ladder : match.getBoard().getLadders()) {
                    if (ladder.getStart() == nextPos) {
                        nextPos = ladder.getEnd();
                        break;
                    }
                }
                match.getBoard().getPlayerPositionHashMap().put(player, nextPos);
                System.out.println("Player " + player.getId() + " going to : " + nextPos);
                if (nextPos == match.getBoard().getMaxPos()) {
                    match.setMatchStatus(MatchStatus.ENDED);
                    match.setWinner(player);
                }
            }
        }
    }
}
