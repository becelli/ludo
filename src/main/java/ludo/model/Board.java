package ludo.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;

public class Board {
    private BoardSquares boardSquares = new BoardSquares();
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

        Pawn pawn = pawns[pawnIndex];
        Square currentSquare = boardSquares.getWherePawnIs(pawn);
        Color pawnColor = pawns[pawnIndex].getColor();
        Square nextSquare = boardSquares.getNextSquare(currentSquare, diceValue, pawnColor);


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
            currentSquare.removePawn(pawns[pawnIndex]);
            nextSquare.removePawn(enemy);

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
        switch (pawn.getColor()) {
            case GREEN -> greenBase[pawn.getIndex() % 4] = null;
            case BLUE -> blueBase[pawn.getIndex() % 4] = null;
        }
    }
    public Color getWinner() {
        EnumMap<Color, ArrayList<Square>> finalSquares = boardSquares.getFinalSquares();
        for (Color color : Color.values()) {
            ArrayList<Square> squares = finalSquares.get(color);
            int lastSquareIndex = squares.size() - 1;
            Square lastSquare = squares.get(lastSquareIndex);
            if (lastSquare.getPawns().size() == 4) {
                return color;
            }
        }
        return null;
    }
}
