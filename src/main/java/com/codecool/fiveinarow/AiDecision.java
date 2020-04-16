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

    public boolean horizontalCheck() {
        int[][][] boardWithCoords = new int[board.length][board[0].length][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boardWithCoords[i][j] = new int[]{board[i][j], i, j};
            }
        }
        int[][] decisionArray = decisionChecker(boardWithCoords, new int[]{1, 1, 0});
        int[] coords = getCoords(decisionArray);
        return true;
        //return winChecker(board);
    }

    private int[] getCoords(int[][] decisionArray) {
        for (int[] elements : decisionArray) {
            if (elements[0] == 0) {
                return new int[]{elements[1], elements[2]};
            }
        }
    }

    /*public boolean verticalCheck() {
        int[][] transposedBoard = matrixTranspose(board);
        return winChecker(transposedBoard);
    }*/

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
                    System.out.println(decisionArrayWithCoords);
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

    /*public boolean hasWonDiagonally() {
        boolean[] booleanListForDiagonals = new boolean[board.length - (howMany - 1)];
        for (int i = (howMany - 1); i < board.length; i++) {
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
    }*/
}
