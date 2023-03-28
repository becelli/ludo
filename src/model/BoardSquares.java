package model;

import java.util.ArrayList;

public class BoardSquares
{
    private static final int DEFAULT_SIZE = 52;
    private ArrayList<Square> squares = new ArrayList<Square>(DEFAULT_SIZE);
    private static int greenStart = 0;
    private static int greenEnd = 50;
    private static int blueStart = 26;
    private static int blueEnd = 24;
    public BoardSquares() {
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            boolean isStart = i % 13 == 0;
            boolean isStar = i % 13 == 6;
            boolean isSpecial = isStart || isStar;
            squares.add(new Square(isSpecial));
        }
    }
    public Square getWherePawnIs(int pawnIndex) {
        return squares.stream().filter(square -> square.getPawns().contains(pawnIndex)).findFirst().get();
    }
    public int indexOf(Square square) {
        return squares.indexOf(square);
    }
    public Square get(int index) {
        return squares.get(index);
    }
    public void addPawnToStart(Pawn pawn) {
        switch (pawn.getColor()) {
            case GREEN -> squares.get(greenStart).addPawn(pawn);
            case BLUE -> squares.get(blueStart).addPawn(pawn);
            default -> throw new IllegalArgumentException("Invalid color");
        }
    }
    public Square getNextSquare(int currentSquareIndex, int diceValue, Color pawnColor, ArrayList<Square> finalSquares) {
        int nextSquareIndex = currentSquareIndex + diceValue;
        boolean hasPassedFinalSquare = false;
        int difference = 0;

        switch (pawnColor) {
            case GREEN -> {
                if (nextSquareIndex > greenEnd) {
                    hasPassedFinalSquare = true;
                    difference = nextSquareIndex - greenEnd;
                }
            }
            case BLUE -> {
                if (nextSquareIndex > blueEnd) {
                    hasPassedFinalSquare = true;
                    difference = nextSquareIndex - blueEnd;
                }
            }
        }

        if (hasPassedFinalSquare) {
            // Todo: verify if this is correct (+ or - 1)
            int index = finalSquares.size() - difference;
            return finalSquares.get(index);
        } else {
            return squares.get(nextSquareIndex);
        }
    }
}

