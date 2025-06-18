package com.ticktactoe;


public class TicTacToeDemo {
    public static void main(String[] args) {
        Player player1 = new Player("p1", Symbol.O);
        Player player2 = new Player("p2", Symbol.X);

        int i = 1;
        while (true && i < 5) {
            System.out.println("\nStarting Match " + i);
            Board board = new Board(3); // ✅ NEW board for each match
            Match match = new Match("m" + i, board, player1, player2);
            match.play();
            System.out.println("Final Match Status: " + match.getMatchStatus());
            i++;
        }
    }
}
