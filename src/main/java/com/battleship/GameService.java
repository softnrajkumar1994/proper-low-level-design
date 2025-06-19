package com.battleship;

public class GameService {
    public static void main(String args[]) {
        Game game = new Game(10);
        Player player1 = new Player("P1");
        player1.setXFrom(0);
        player1.setXTo(4);


        Player player2 = new Player("P2");
        player2.setXFrom(5);
        player2.setXTo(9);

        game.setPlayer1(player1);
        game.setPlayer2(player2);

        game.addShip(player1,2,2,2);
        game.addShip(player1,3,3,2);

        game.addShip(player2,7,7,2);
        game.addShip(player2,8,8,2);

        game.play();

    }
}
