package model;

public class Pawn {
    private final Color color;
    private final int index;


    public Pawn(Color color, int index) {
        this.color = color;
        this.index = index;
    }

    public Color getColor() {
        return this.color;
    }

    public int getIndex() {
        return this.index;
    }

}
