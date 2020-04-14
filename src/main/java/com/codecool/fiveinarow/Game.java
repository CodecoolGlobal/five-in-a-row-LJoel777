package com.codecool.fiveinarow;

import java.util.Arrays;
import java.util.Scanner;

public class Game implements GameInterface {

    private int[][] board;

    public Game(int nRows, int nCols) {
        board = new int[nRows][nCols];
        for (int[] row : board) {
            for (int col : row) {
                row[col] = 0;
            }
        }
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
        System.out.println("Player" + player + " turn: ");
        String move = input.next().toUpperCase();
        MoveChecker checker = new MoveChecker(move, this.board);
        boolean validMove = checker.validMove();
        if (!validMove) {
            System.out.println("Invalid input!");
            getMove(player);
        }
        return checker.getColRow();
    }

    public int[] getAiMove(int player) {
        return null;
    }

    public void mark(int player, int row, int col) {

    }

    public boolean hasWon(int player, int howMany) {
        return false;
    }

    public boolean isFull() {
        return false;
    }

    public void printBoard() {
    }

    public void printResult(int player) {
    }

    public void enableAi(int player) {
    }

    public void play(int howMany) {
    }
}
