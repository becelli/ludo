package entities;

public class Board {
    private final int size = 52;
    private Pawn[] pawns = new Pawn[size];

    public LudoBoard() {
        for (int i = 0; i < size; i++) {
            pawns[i] = null;
        }
    }

    public void addPawn(Pawn pawn, int position) {
        pawns[position] = pawn;
    }

    public void movePawn(Pawn pawn, int distance) {
//        Calculate new position based on color
//        Check if new position is occupied
//        If occupied, check if it is a friendly pawn
//        If friendly, do nothing
//        If enemy, remove enemy pawn and move pawn


    }
}
