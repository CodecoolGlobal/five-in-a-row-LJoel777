package com.codecool.fiveinarow;

public class Play {
    public static void main(String[] args) {
        int player = 1;
        int count = 0;
        Game gomoku = new Game(5, 4);
        do {
            gomoku.printBoard();
            if (player == 1)
                player = 2;
            else
                player = 1;
            int[] move = gomoku.getMove(player);
            gomoku.mark(player, move[0], move[1]);
            count++;
        } while (count <= 10);
    }
}
