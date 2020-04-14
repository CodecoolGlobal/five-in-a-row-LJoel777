package com.codecool.fiveinarow;

import java.util.*;

public class MoveChecker {
    static int[][] board;
    static String move;

    public MoveChecker(String userMove, int[][] boardNew) {
        board = boardNew;
        move = userMove;
    }

    public static ArrayList<Integer> validCols() {
        int colMaxIndex = board[0].length;

        ArrayList<Integer> validCols = new ArrayList<>();

        for (int i = 0; i < colMaxIndex; i++) {
            validCols.add(i);
        }

        return validCols;
    }

    public static Map<Character, Integer> validRows() {
        int rowMaxIndex = board.length - 1;

        Map<Character, Integer> validRows = new HashMap<>();

        int charWithNum = 65;
        for (int i = 0; i <= rowMaxIndex; i++) {
            validRows.put((char) charWithNum, i);
            charWithNum++;
        }

        return validRows;
    }

    public static int colToInt() {
        char[] input = move.toCharArray();
        int col;
        try {
            if (input.length > 2)
                col = Integer.parseInt(String.valueOf(Arrays.copyOfRange(input, 1, input.length)));
            else
                col = Integer.parseInt(String.valueOf(input[1]));
            return col-1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
            return -1;
        }
    }

    public boolean validMove() {
        char[] input = move.toCharArray();
        boolean valid = false;
        ArrayList<Integer> validCols = validCols();
        int col = colToInt();
        Map<Character, Integer> validRows = validRows();
        if ((validRows.containsKey(input[0])) && (col > 0 && validCols.contains(col)))
            valid = true;
        return valid;
    }
}
