package com.codecool.fiveinarow;

import java.util.Scanner;
import java.util.Arrays;

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
        MoveChecker checker = new MoveChecker(move, this.board, player);
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
        String randomNum = String.valueOf(Randomize.generate(0, 10));
        char randomABC = (char) Randomize.generate(65, 65+this.board.length-1);
        String move = randomABC + randomNum;
        System.out.println(move);
        MoveChecker checker = new MoveChecker(move, this.board ,player);
        boolean validMove = checker.validMove();
        if (!validMove) {
            getAiMove(player);
        } else if (this.board[checker.getColRow()[0]][checker.getColRow()[1]] != 0) {
            getAiMove(player);
        }
        return checker.getColRow();
    }

    @Override
    public void mark(int player, int row, int col) {
        if (this.board[row][col] == 0) {
            this.board[row][col] = player;
        }
    }

    public boolean hasWon(int player, int howMany) {
        int[] fullArray = new int[howMany];
        for (int i = 0; i < howMany; i++) {
            fullArray[i] = player;
        }
        //horizontally
        for (int[] row : this.board) {
            int[] winArray = new int[howMany];
            int counter = 0;
            for (int cell : row) {
                if (cell == player) {
                    winArray[counter] = player;
                    counter += 1;
                    if (Arrays.equals(winArray, fullArray)) {
                        return true;
                    }
                } else {
                    counter = 0;
                    for (int i = 0; i < winArray.length; i++) {
                        if (winArray[i] == player) {
                            winArray[i] = 0;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isFull() {
        boolean isFull = false;
        boolean[] booleanArray = new boolean[this.board.length];
        for (int i = 0; i < this.board.length; i++) {
            for (int boardCell : this.board[i]) {
                if (boardCell == 0) {
                    booleanArray[i] = false;
                    break;
                } else {
                    booleanArray[i] = true;
                }
            }
        }
        for (boolean element : booleanArray) {
            if (!element) {
                isFull = false;
                break;
            } else {
                isFull = true;
            }
        }
        return isFull;
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
        int[] move = getAiMove(player);
        mark(player, move[0], move[1]);
    }

    public void play(int howMany, int gameMode) {
        int player = 1;
        if (gameMode == 1) {
            do {
                printBoard();
                int[] move = getMove(player);
                mark(player, move[0], move[1]);
                if (player == 1)
                    player = 2;
                else
                    player = 1;
            } while (!isFull() || !hasWon(player, howMany));
        } else {
            do {
                printBoard();
                int[] move = getMove(player);
                mark(player, move[0], move[1]);
                player = 2;
                enableAi(player);
                player = 1;
            } while (!isFull() || !hasWon(player, howMany));
        }
    }
}
