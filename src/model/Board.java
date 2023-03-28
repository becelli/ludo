package model;
import java.util.ArrayList;

public class Board {
    private BoardSquares boardSquares = new BoardSquares();
    private ArrayList<Square> greenSquares = new ArrayList<Square>(7);
    private ArrayList<Square> blueSquares = new ArrayList<Square>(7);
    private Pawn[] greenBase = new Pawn[4];
    private Pawn[] blueBase = new Pawn[4];
    private Pawn[] pawns = new Pawn[16];
    public Board() {
        for (int i = 0; i < 4; i++) {
            pawns[i] = new Pawn(Color.GREEN, i);
            pawns[i + 8] = new Pawn(Color.BLUE, i);
            greenBase[i] = pawns[i];
            blueBase[i] = pawns[i + 8];
        }
    }
    public void movePawn(int pawnIndex, int diceValue) {
        // Verify because of the recursion
        if (diceValue == 0) {
            return;
        }

        Square currentSquare = boardSquares.getWherePawnIs(pawnIndex);
        int currentSquareIndex = boardSquares.indexOf(currentSquare);

        // Get the next square
        Color pawnColor = pawns[pawnIndex].getColor();
        ArrayList<Square> finalSquares = null;
        switch (pawnColor) {
            case GREEN -> finalSquares = greenSquares;
            case BLUE -> finalSquares = blueSquares;
        }
        Square nextSquare = boardSquares.getNextSquare(currentSquareIndex, diceValue, pawnColor, finalSquares);


        // Verify if the next square is special
        if (nextSquare.isSpecial()) {
            nextSquare.addPawn(pawns[pawnIndex]);
            currentSquare.removePawn(pawns[pawnIndex]);
            return;
        }

        // Rule to move to a non-special square
        int enemyCount = nextSquare.enemyPawnsCount(pawns[pawnIndex]);
        if (enemyCount > 1) {
            // Go to one square before the next square (as if the dice value was decreased by 1)
            movePawn(pawnIndex, diceValue - 1);
        } else if (enemyCount == 1) {
            // Move to square and send enemy back to base.
            Pawn enemy = nextSquare.getPawns().stream().filter(p -> p.getColor() != pawns[pawnIndex].getColor()).findFirst().get();
            nextSquare.addPawn(pawns[pawnIndex]);
            currentSquare.removePawn(pawns[enemy.getIndex()]);
            switch (enemy.getColor()) {
                case BLUE -> blueBase[enemy.getIndex() % 4] = enemy;
                case GREEN -> greenBase[enemy.getIndex() % 4] = enemy;
            }
        } else {
            nextSquare.addPawn(pawns[pawnIndex]);
            currentSquare.removePawn(pawns[pawnIndex]);
        }
    }
    public void addPawnToBoard(int pawnIndex) {
        Pawn pawn = pawns[pawnIndex];
        boardSquares.addPawnToStart(pawn);
    }
    public Color getWinner() {
        if (greenSquares.get(6).getPawns().size() == 4) {
            return Color.GREEN;
        }
        if (blueSquares.get(6).getPawns().size() == 4) {
            return Color.BLUE;
        }
        return null;
    }
}
