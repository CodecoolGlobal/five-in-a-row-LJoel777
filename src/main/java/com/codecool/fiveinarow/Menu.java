package com.codecool.fiveinarow;

import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        int size = checkSize();
        int howMany = howManny();
        int gameMode = gameMode();
        Game gomoku = new Game(size, size);
        gomoku.play(howMany, gameMode);
    }

    public static int checkSize() {
        Scanner input = new Scanner(System.in);
        System.out.println("Chose a board size (10, 15, 19): ");
        String size = input.next().toUpperCase();
        if (size.equals("Q") || size.equals("QUIT"))
            System.exit(0);
        if (size.equals("15") || size.equals("19") || size.equals("10"))
            return Integer.parseInt(size);
        System.out.println("Invalid size! Please try again! (Valid size: 10, 15, 19)");
        return checkSize();
    }

    public static int howManny() {
        Scanner input = new Scanner(System.in);
        System.out.println("How many mark needed to win? (3, 5)");
        String size = input.next().toUpperCase();
        if (size.equals("Q") || size.equals("QUIT"))
            System.exit(0);
        if (size.equals("3") || size.equals("5")) {
            return Integer.parseInt(size);
        }
        System.out.println("Please try again! (3, 5)");
        return howManny();
    }

    public static int gameMode() {
        Scanner input = new Scanner(System.in);
        System.out.println("Chose game mode! (HUMAN VS HUMAN: 1, AI VS HUMAN: 2)");
        String gameMode = input.next().toUpperCase();
        if (gameMode.equals("Q") || gameMode.equals("QUIT"))
            System.exit(0);
        if (gameMode.equals("1") || gameMode.equals("2")) {
            return Integer.parseInt(gameMode);
        }
        System.out.println("Please try again! (HUMAN VS HUMAN: 1, AI VS HUMAN: 2)");
        return gameMode();
    }
}
