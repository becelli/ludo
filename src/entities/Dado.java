package entities;

public class Dado {
    public static int rolar() {
        return (int) (Math.random() * 6 + 1);
    }
}
