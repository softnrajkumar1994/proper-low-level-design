package com.snakeandladder;

import java.util.ArrayList;
import java.util.List;

public class SnakeAndLadderGame {
    public static void play(Match match) {
        match.setMatchStatus(MatchStatus.IN_PROGRESS);
        initializePlayers(match);

        while (match.getMatchStatus() != MatchStatus.ENDED) {
            for (Player player : match.getPlayerList()) {
                if (match.getMatchStatus() == MatchStatus.ENDED) break;

                int roll = match.getDice().play();
                int currentPos = match.getBoard().getPlayerPositionHashMap().get(player);
                int newPos = computeNextPosition(currentPos, roll);

                if (!isValidMove(currentPos, roll, match.getBoard().getMaxPos())) {
                    log(player.getPlayerName() + " rolled " + roll + " but can't move.");
                    continue;
                }

                newPos = applySnakesAndLadders(newPos, match.getBoard());

                match.getBoard().getPlayerPositionHashMap().put(player, newPos);
                log(player.getPlayerName() + " rolled " + roll + " and moved to " + newPos);

                if (newPos == match.getBoard().getMaxPos()) {
                    match.setMatchStatus(MatchStatus.ENDED);
                    match.setWinner(player);
                    log("🎉 " + player.getPlayerName() + " has won the game!");
                    break;
                }
            }
        }
    }

// ---------------- Helper Methods ---------------- //

    private static void initializePlayers(Match match) {
        for (Player player : match.getPlayerList()) {
            match.getBoard().getPlayerPositionHashMap().put(player, -1);
        }
    }

    private static int computeNextPosition(int currentPos, int roll) {
        return (currentPos == -1) ? ((roll == 1) ? 1 : -1) : currentPos + roll;
    }

    private static boolean isValidMove(int currentPos, int roll, int maxPos) {
        if (currentPos == -1 && roll != 1) return false;
        return (currentPos + roll) <= maxPos;
    }

    private static int applySnakesAndLadders(int pos, Board board) {
        for (Snake snake : board.getSnakes()) {
            if (snake.getHead() == pos) {
                log("🐍 Bitten by snake! Down to " + snake.getTail());
                return snake.getTail();
            }
        }

        for (Ladder ladder : board.getLadders()) {
            if (ladder.getStart() == pos) {
                log("🪜 Climbed a ladder! Up to " + ladder.getEnd());
                return ladder.getEnd();
            }
        }

        return pos;
    }

    private static void log(String msg) {
        System.out.println(msg);
    }

    public static List<Player> createPlayers(int n) {
        List<Player> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(new Player("P" + i, "Player_" + i));
        }
        return list;
    }

    public static List<Snake> createSnakes() {
        return List.of(
                new Snake(99, 78),
                new Snake(95, 56),
                new Snake(87, 24),
                new Snake(62, 18),
                new Snake(48, 26),
                new Snake(36, 6)
        );
    }

    public static List<Ladder> createLadders() {
        return List.of(
                new Ladder(2, 23),
                new Ladder(8, 34),
                new Ladder(20, 77),
                new Ladder(32, 68),
                new Ladder(41, 79),
                new Ladder(74, 92)
        );
    }


}
