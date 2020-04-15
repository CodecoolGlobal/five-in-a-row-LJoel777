package com.codecool.fiveinarow;

public class Randomize {
    public static int generate(int min,int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
