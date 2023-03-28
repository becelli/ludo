package model;

import java.util.ArrayList;

public class Square {
    private final Boolean isSpecial;
    private ArrayList<Pawn> pawns;

    public Square(Boolean isSpecial) {
        this.isSpecial = isSpecial;
        this.pawns = new ArrayList<Pawn>();
    }

    public Boolean isSpecial() {
        return this.isSpecial;
    }

    public void addPawn(Pawn pawn) {
        this.pawns.add(pawn);
    }

    public void removePawn(Pawn pawn) {
        this.pawns.remove(pawn);
    }

    public ArrayList<Pawn> getPawns() {
        return this.pawns;
    }

    public int enemyPawnsCount(Pawn pawn) {
        return (int) this.pawns.stream().filter(p -> p.getColor() != pawn.getColor()).count();
    }

}
