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

    public int[] horizontalCheck(int[] inputArray) {
        int[][][] boardWithCoords = new int[board.length][board[0].length][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boardWithCoords[i][j] = new int[]{board[i][j], i, j};
            }
        }
        int[][] decisionArray = decisionChecker(boardWithCoords, inputArray);
        if (decisionArray != null) {
            return getCoords(decisionArray);
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

    public int[] verticalCheck(int[] inputArray) {
        int[][][] transposedBoardWithCoords = new int[board[0].length][board.length][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                transposedBoardWithCoords[j][i] = new int[]{board[i][j], i, j};
            }
        }
        int[][] decisionArray = decisionChecker(transposedBoardWithCoords, inputArray);
        if (decisionArray != null) {
            int[] coords = getCoords(decisionArray);
            System.out.println(Arrays.toString(coords));
            return coords;
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
                Arrays.sort(decisionArray);
                Arrays.sort(arrayToCheck);
                if (Arrays.equals(decisionArray, arrayToCheck)) {
                    return decisionArrayWithCoords;
                }
            }
        }
        return null;
    }


    /*public int[][] permutation() {
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
    }*/

    public int[] diagonalCheck(int[] inputArray) {
        for (int i = (howMany - 1); i < board.length; i++) {
            int[][][] boardOfDiagonals = new int[4][i + 1][3];
            int[][] rowTopLeft = new int[i + 1][3];
            int[][] rowTopRight = new int[i + 1][3];
            int[][] rowBottomLeft = new int[i + 1][3];
            int[][] rowBottomRight = new int[i + 1][3];

            for (int j = 0; j < i + 1; j++) {
                rowTopLeft[j] = new int[]{board[i - j][j], i - j, j};
                rowTopRight[j] = new int[]{board[j][board.length - 1 - (i - j)], j, board.length - 1 - (i - j)};
                rowBottomLeft[j] = new int[]{board[board.length - 1 - j][i - j], board.length - 1 - j, i - j};
                rowBottomRight[j] = new int[]{board[board.length - 1 - (i - j)][board.length - 1 - j], board.length - 1 - (i - j), board.length - 1 - j};
            }
            boardOfDiagonals[0] = rowTopLeft;
            boardOfDiagonals[1] = rowTopRight;
            boardOfDiagonals[2] = rowBottomLeft;
            boardOfDiagonals[3] = rowBottomRight;
            int[][] decisionArray = decisionChecker(boardOfDiagonals, inputArray);
            if (decisionArray != null) {
                int[] coords = getCoords(decisionArray);
                System.out.println(Arrays.toString(coords));
                return coords;
            }
        }
        return null;
    }
}
