package com.battleship;

import com.cricbuzz.MatchService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@NoArgsConstructor
public class Game {
    private Cell[][] board;
    private Player player1;
    private Player player2;
    private Player winner;
    private MatchStatus matchStatus = MatchStatus.NOT_STARTED;

    public Game(int size) {
        if (size % 2 == 1) {
            throw new RuntimeException("Size cannot be odd");
        }
        board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
        matchStatus = MatchStatus.IN_PROGRESS;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;

    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void addShip(Player player, int x, int y, int size) {
        List<Cell> cellList = new ArrayList<>();
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                Cell c = new Cell(i, j);
                cellList.add(c);

                board[i][j] = c;
            }
        }


        for (int i = x - size; i < x; i++) {
            for (int j = y - size; j < y; j++) {
                Cell c = new Cell(i, j);
                cellList.add(c);
                board[i][j] = c;
            }
        }

        Ship ship = new Ship(player.getPlayerName() + "_ship" + player.getShipList().size() + 1);
        ship.setCoordinates(cellList);
        player.getShipList().add(ship);
    }

    public void play() {
        boolean isFirstPlayer = true;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Set<String> firedCoordinates = new HashSet<>();

        while (matchStatus == MatchStatus.IN_PROGRESS) {
            Player currentPlayer = isFirstPlayer ? player1 : player2;
            Player opponent = isFirstPlayer ? player2 : player1;

            int x, y;
            // Ensure no duplicate missile coordinates
            do {
                x = random.nextInt(opponent.getXFrom(), opponent.getXTo() + 1);
                y = random.nextInt(0, board.length);
            } while (!firedCoordinates.add(x + "," + y));

            boolean hit = false;
            Iterator<Ship> shipIterator = opponent.getShipList().iterator();

            while (shipIterator.hasNext()) {
                Ship ship = shipIterator.next();
                for (Cell cell : ship.getCoordinates()) {
                    if (cell.getX() == x && cell.getY() == y) {
                        shipIterator.remove();
                        hit = true;
                        System.out.println(currentPlayer.getPlayerName() + " fired at (" + x + "," + y + ") : Hit! " +
                                opponent.getPlayerName() + "'s ship " + ship.getShipName() + " destroyed.");
                        break;
                    }
                }
                if (hit) break;
            }

            if (!hit) {
                System.out.println(currentPlayer.getPlayerName() + " fired at (" + x + "," + y + ") : Miss.");
            }

            System.out.println("Ships Remaining - " + player1.getPlayerName() + ": " +
                    player1.getShipList().size() + ", " +
                    player2.getPlayerName() + ": " +
                    player2.getShipList().size());

            if (opponent.getShipList().isEmpty()) {
                winner = currentPlayer;
                matchStatus = MatchStatus.ENDED;
                System.out.println("GameOver. " + winner.getPlayerName() + " wins.");
                break;
            }

            isFirstPlayer = !isFirstPlayer;
        }
    }

}
