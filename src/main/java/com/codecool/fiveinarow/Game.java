package com.codecool.fiveinarow;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game implements GameInterface {

    private int[][] board;
    private String filler;
    private String playerOneMark;
    private String playerTwoMark;
    private int player = 1;

    public Game(int nRows, int nCols) {
        board = new int[nRows][nCols];
        for (int[] row : board) {
            for (int col : row) {
                row[col] = 0;
            }
        }
        filler = "\033[34;1m.\033[0m";
        playerOneMark = "\033[31;1mX\033[0m";
        playerTwoMark = "\033[33;1;2mO\033[0m";
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }


    public int[] getMove(int player) {
        Scanner input = new Scanner(System.in);
        System.out.println("\n" + "Player" + player + "'s turn: ");
        String move = input.next().toUpperCase();
        if (move.equals("Q") || move.equals("QUIT"))
            System.exit(0);
        MoveChecker checker = new MoveChecker(move, this.board, player);
        boolean validMove = checker.validMove();
        if (!validMove) {
            clear();
            printBoard();
            System.out.println("Invalid input!");
            getMove(player);
        } else if (this.board[checker.getColRow()[0]][checker.getColRow()[1]] != 0) {
            clear();
            printBoard();
            System.out.println("Invalid input!");
            getMove(player);
        }
        return checker.getColRow();
    }

    public int[] getAiMove(int player, int howMany) {
        AiDecision aiMoveChecker = new AiDecision(player, howMany, board);
        for (int i = 1; i < howMany; i++) {
            for (int k = 2; k > 0; k--) {
                int[] arrayToCheck = new int[howMany];
                for (int j = 0; j < (howMany - i); j++) {
                    arrayToCheck[j] = k;
                }
                if (aiMoveChecker.horizontalCheck(arrayToCheck) != null) {
                    return aiMoveChecker.horizontalCheck(arrayToCheck);
                } else if (aiMoveChecker.verticalCheck(arrayToCheck) != null) {
                    return aiMoveChecker.verticalCheck(arrayToCheck);
                } else if (aiMoveChecker.diagonalCheck(arrayToCheck) != null) {
                    return aiMoveChecker.diagonalCheck(arrayToCheck);
                }
            }
        }
        String randomNum = String.valueOf(Randomize.generate(0, 10));
        char randomABC = (char) Randomize.generate(65, 65 + this.board.length - 1);
        String move = randomABC + randomNum;
        MoveChecker checker = new MoveChecker(move, this.board, player);
        boolean validMove = checker.validMove();
        if (!validMove) {
            getAiMove(player, howMany);
        } else if (this.board[checker.getColRow()[0]][checker.getColRow()[1]] != 0) {
            getAiMove(player, howMany);
        }
        return checker.getColRow();
    }

    public void mark(int player, int row, int col) {
        if (this.board[row][col] == 0) {
            this.board[row][col] = player;
        }
    }

    public boolean hasWon(int player, int howMany) {
        WinCondition winCondition = new WinCondition(player, howMany, this.board);
        return winCondition.hasWonHorizontally() || winCondition.hasWonVertically() || winCondition.hasWonDiagonally();
    }

    public boolean isFull() {
        boolean isFull = false;
        boolean[] booleanArray = new boolean[this.board.length];
        for (int i = 0; i < this.board.length; i++) {
            for (int boardCell : this.board[i]) {
                if (boardCell == 0) {
                    return false;
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

    public void printResult(int user) {
        if (user == 0)
            System.out.println("It's tie!");
        else if (user == 1)
            System.out.println("X" + " won!");
        else
            System.out.println("O" + " won!");
    }

    public void enableAi(int player, int howMany) {
        int[] move = getAiMove(player, howMany);
        mark(player, move[0], move[1]);
    }

    public void play(int howMany, int gameMode) throws InterruptedException {
        if (gameMode == 1)
            this.humanVsHuman(howMany);
        else
            this.AiVsHuman(howMany);
    }

    public void humanVsHuman(int howMany) throws InterruptedException {
        while (!hasWon(player, howMany)) {
            clear();
            this.changePlayer();
            printBoard();
            int[] move = getMove(player);
            mark(player, move[0], move[1]);
            if (isFull()) {
                printResult(0);
                break;
            }
        }
        clear();
        printBoard();
        printResult(player);
        restart();
    }

    public void AiVsHuman(int howMany) throws InterruptedException {
        while (!hasWon(player, howMany)) {
            clear();
            this.changePlayer();
            if (player == 1) {
                printBoard();
                int[] move = getMove(player);
                mark(player, move[0], move[1]);
            } else {
                printBoard();
                TimeUnit.SECONDS.sleep(1);
                enableAi(player, howMany);
            }
            if (isFull()) {
                printResult(0);
                restart();
                break;
            }
        }
        clear();
        printBoard();
        printResult(player);
        restart();
    }

    public void changePlayer() {
        if (this.player == 1)
            player = 2;
        else
            player = 1;
    }

    public void clear() {
        System.out.print("\033[H\033[2J");
    }

    public void restart() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n" + "Do you want to play again? (Yes/No)");
        String yesOrNo = input.next().toUpperCase();
        if (yesOrNo.equals("YES") || yesOrNo.equals("Y"))
            Menu.start();
        else if (yesOrNo.equals("NO") || yesOrNo.equals("N"))
            System.exit(0);
        else {
            System.out.println("Please write yes or no!");
            restart();
        }
    }
}
