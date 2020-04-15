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
}
