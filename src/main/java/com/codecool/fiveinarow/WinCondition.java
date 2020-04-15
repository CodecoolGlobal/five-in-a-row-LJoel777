package com.codecool.fiveinarow;

import java.util.Arrays;

public class WinCondition {
    static int[] fullArray;
    static int[][] board;
    int player;
    int howMany;

    public WinCondition(int player, int howMany, int[][] boardNew) {
        board = boardNew;
        fullArray = new int[howMany];
        for (int i = 0; i < howMany; i++) {
            fullArray[i] = player;
        }
        this.player = player;
        this.howMany = howMany;
    }

    public boolean hasWonHorizontally() {
        return winChecker(board);
    }

    public boolean hasWonVertically() {
        int [][] transposedBoard = matrixTranspose(board);
        return winChecker(transposedBoard);
    }

    private boolean winChecker(int[][] newBoard) {
        for (int[] row : newBoard) {
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

    public int[][] matrixTranspose(int[][] oldBoard) {
        int[][] transposedBoard = new int[oldBoard[0].length][oldBoard.length];
        for (int i = 0; i < oldBoard.length; i++)  {
            for (int j = 0; j < oldBoard[0].length; j++) {
                transposedBoard[j][i] = oldBoard[i][j];
            }
        }
        return transposedBoard;
    }

    public boolean hasWonDiagonally() {
        boolean[] booleanListForDiagonals = new boolean[board.length - (howMany - 1)];
        for (int i = (howMany - 1) ; i < board.length; i++) {
            int[][] boardOfDiagonals = new int[4][i + 1];
            int[] rowTopLeft = new int[i + 1];
            int[] rowTopRight = new int[i + 1];
            int[] rowBottomLeft = new int[i + 1];
            int[] rowBottomRight = new int[i + 1];

            for (int j = 0; j < i + 1; j++) {
                rowTopLeft[j] = board[i - j][j];
                rowTopRight[j] = board[j][board.length - 1 - (i - j)];
                rowBottomLeft[j] = board[board.length - 1 - (i - j)][j];
                rowBottomRight[j] = board[board.length - 1 - j][board.length - 1 - (i - j)];
            }
            boardOfDiagonals[0] = rowTopLeft;
            boardOfDiagonals[1] = rowTopRight;
            boardOfDiagonals[2] = rowBottomLeft;
            boardOfDiagonals[3] = rowBottomRight;
            booleanListForDiagonals[i - (howMany - 1)] = winChecker(boardOfDiagonals);
        }
        for (boolean element : booleanListForDiagonals) {
            if (element) {
                return true;
            }
        }
        return false;
    }
}
