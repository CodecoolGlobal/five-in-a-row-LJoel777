package com.codecool.fiveinarow;

import java.util.Scanner;

public class Game implements GameInterface {

    private int[][] board;
    private String filler;
    private String playerOneMark;
    private String playerTwoMark;

    public Game(int nRows, int nCols) {
        board = new int[nRows][nCols];
        for (int[] row : board) {
            for (int col : row) {
                row[col] = 0;
            }
        }
        filler = ".";
        playerOneMark = "X";
        playerTwoMark = "O";
    }

    @Override
    public int[][] getBoard() {
        return board;
    }

    @Override
    public void setBoard(int[][] board) {
        this.board = board;
    }

    @Override
    public int[] getMove(int player) {
        Scanner input = new Scanner(System.in);
        System.out.println("Player" + player + "'s turn: ");
        String move = input.next().toUpperCase();
        if (move.equals("Q") || move.equals("QUIT"))
            System.exit(0);
        MoveChecker checker = new MoveChecker(move, this.board);
        boolean validMove = checker.validMove();
        if (!validMove) {
            System.out.println("Invalid input!");
            getMove(player);
        } else if (this.board[checker.getColRow()[0]][checker.getColRow()[1]] != 0) {
            System.out.println("Invalid input!");
            getMove(player);
        }
        return checker.getColRow();
    }

    public int[] getAiMove(int player) {
        return null;
    }

    @Override
    public void mark(int player, int row, int col) {
        if (this.board[row][col] == 0) {
            this.board[row][col] = player;
        }
    }

    public boolean hasWon(int player, int howMany) {
        return false;
    }

    @Override
    public boolean isFull() {
        boolean[] booleanArray = new boolean[this.board.length];
        for (int i = 0; i < this.board.length; i++) {
            for (int boardCell : this.board[i]) {
                if (boardCell == 0) {
                    break;
                } else {
                    booleanArray[i] = true;
                }
            }
        }
        for (boolean element : booleanArray) {
            if (!element) {
                break;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void printBoard() {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.print(" ");
        for (int i = 0; i < this.board[0].length; i++) {
            if (i + 1 < 10) {
                System.out.print("   ");
            } else {
                System.out.print("  ");
            }
            System.out.print(i + 1);
        }
        System.out.print("\n");
        for (int i = 0; i < this.board.length; i++) {
            System.out.print(abc.charAt(i));
            for (int j = 0; j < this.board[i].length; j++) {
                System.out.print("   ");
                switch (this.board[i][j]) {
                    case 0:
                        System.out.print(this.filler);
                        break;
                    case 1:
                        System.out.print(this.playerOneMark);
                        break;
                    case 2:
                        System.out.print(this.playerTwoMark);
                        break;
                }
            }
            System.out.print("\n");
        }
    }

    public void printResult(int player) {
    }

    public void enableAi(int player) {
    }

    public void play(int howMany) {
        int player = 1;
        do {
            printBoard();
            if (player == 1)
                player = 2;
            else
                player = 1;
            int[] move = getMove(player);
            System.out.println("\n\n\n\n\n\n");
            mark(player, move[0], move[1]);
        } while (isFull());
    }
}
