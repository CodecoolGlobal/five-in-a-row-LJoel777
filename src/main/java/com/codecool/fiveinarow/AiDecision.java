package com.codecool.fiveinarow;

import java.util.Arrays;

public class AiDecision {
    static int[][] board;
    int player;
    int howMany;

    public AiDecision(int player, int howMany, int[][] boardNew) {
        board = boardNew;
        this.player = player;
        this.howMany = howMany;
    }

    public int[] horizontalCheck() {
        int[][] perArrays = permutation();
        for (int row = 0; row <= perArrays.length - 1; row++) {
            int[] array = perArrays[row];
            int[][][] boardWithCoords = new int[board.length][board[0].length][3];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    boardWithCoords[i][j] = new int[]{board[i][j], i, j};
                }
            }
            int[][] decisionArray = decisionChecker(boardWithCoords, array);
            if (decisionArray != null) {
                return getCoords(decisionArray);
            }
        }
        return null;
    }

    private int[] getCoords(int[][] decisionArray) {
        for (int[] elements : decisionArray) {
            if (elements[0] == 0) {
                return new int[]{elements[1], elements[2]};
            }
        }
        return null;
    }


    private int[][] decisionChecker(int[][][] newBoard, int[] arrayToCheck) {
        for (int[][] row : newBoard) {
            int[][] decisionArrayWithCoords = new int[howMany][3];
            int[] decisionArray = new int[howMany];
            for (int i = 0; i < row.length - (howMany - 1); i++) {
                for (int j = 0; j < howMany; j++) {
                    decisionArrayWithCoords[j] = row[i + j];
                    decisionArray[j] = row[i + j][0];
                }
                if (Arrays.equals(decisionArray, arrayToCheck)) {
                    return decisionArrayWithCoords;
                }
            }
        }
        return null;
    }

    public int[][] matrixTranspose(int[][] oldBoard) {
        int[][] transposedBoard = new int[oldBoard[0].length][oldBoard.length];
        for (int i = 0; i < oldBoard.length; i++) {
            for (int j = 0; j < oldBoard[0].length; j++) {
                transposedBoard[j][i] = oldBoard[i][j];
            }
        }
        return transposedBoard;
    }

    public int[][] permutation() {
        int[][] permArr = new int[howMany][howMany];
        for (int j = 0; j <= howMany - 1; j++) {
            for (int i = 0; i <= howMany - 1; i++) {
                permArr[j][i] = 1;
                if (i == j) {
                    permArr[j][i] = 0;
                }
            }
        }
        return permArr;
    }
}
