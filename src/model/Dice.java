package model;

public class Dice {
    public static int rolar() {
        return (int) (Math.random() * 6 + 1);
    }
}
