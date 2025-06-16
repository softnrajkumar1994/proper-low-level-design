package com.ticktactoe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;


@Getter
@Setter
@NoArgsConstructor
public class Match {
    private String matchId;
    private Board board;
    private Player player1;
    private Player player2;
    private MatchStatus matchStatus = MatchStatus.NOT_STARTED;

    public Match(String matchId, Board board, Player player1, Player player2) {
        this.matchId = matchId;
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    private boolean isMatchEnded() {
        return MatchStatus.ENDED.equals(matchStatus) || MatchStatus.DRAWN.equals(matchStatus);
    }

    public void play() {
        Player currentPlayer = player1;
        Random random = new Random();

        this.matchStatus = MatchStatus.IN_PROGRESS;

        while (!isMatchEnded() && !board.getEmptySlots().isEmpty()) {
            int cellIndex = random.nextInt(board.getEmptySlots().size());
            Cell cell = board.getEmptySlots().remove(cellIndex);
            int x = cell.getX();
            int y = cell.getY();

            board.getGrid()[x][y].setSymbol(currentPlayer.getSymbol());

            if (new WinningStrategy().isMatches(this, currentPlayer.getSymbol(), x, y)) {
                matchStatus = MatchStatus.ENDED;
                System.out.println("Player " + currentPlayer.getPlayerId() + " wins!");
                return;
            }

            if (board.getEmptySlots().isEmpty()) {
                matchStatus = MatchStatus.DRAWN;
                System.out.println("Match is a draw!");
                return;
            }

            currentPlayer = currentPlayer == player1 ? player2 : player1;
        }
    }
}
