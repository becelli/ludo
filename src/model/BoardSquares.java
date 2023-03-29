package model;

import java.util.ArrayList;
import java.util.EnumMap;

public class BoardSquares
{
    private static final Integer DEFAULT_SIZE = 52;
    private static final Integer DEFAULT_FINAL_SQUARES_SIZE = 6;
    private static final Integer GREEN_START = 0;
    private static final Integer GREEN_END = 50;
    private static final Integer BLUE_END = 24;
    private static final Integer BLUE_START = 26;
    private ArrayList<Square> squares = new ArrayList<Square>(DEFAULT_SIZE);
    private EnumMap<Color, ArrayList<Square>> finalSquares = new EnumMap<Color, ArrayList<Square>>(Color.class);
    public BoardSquares() {
        for (Color color : Color.values()) {
            finalSquares.put(color, new ArrayList<Square>(DEFAULT_FINAL_SQUARES_SIZE));
        }

        for (int i = 0; i < DEFAULT_SIZE; i++) {
            boolean isStart = i % 13 == 0;
            boolean isStar = i % 13 == 6;
            boolean isSpecial = isStart || isStar;
            squares.add(new Square(isSpecial));
        }
    }

    public Square getWherePawnIs(Pawn pawn) {
        Square pawnSquare = squares.stream().filter(square -> square.getPawns().contains(pawn)).findFirst().orElse(null);
        if (pawnSquare == null) {
            pawnSquare = finalSquares.get(pawn.getColor()).stream().filter(square -> square.getPawns().contains(pawn)).findFirst().orElse(null);
        }
        return pawnSquare;
    }
    public Square get(int index) {
        return squares.get(index);
    }
    public void addPawnToStart(Pawn pawn) {
        switch (pawn.getColor()) {
            case GREEN -> squares.get(GREEN_START).addPawn(pawn);
            case BLUE -> squares.get(BLUE_START).addPawn(pawn);
            default -> throw new IllegalArgumentException("Invalid color");
        }
    }
    public EnumMap<Color, ArrayList<Square>> getFinalSquares() {
        return finalSquares;
    }
    public Square getNextSquare(Square currentSquare, int diceValue, Color pawnColor) {
        boolean isInFinalSquares = finalSquares.get(pawnColor).contains(currentSquare);
        if (isInFinalSquares) {
            int nextIndex = finalSquares.get(pawnColor).indexOf(currentSquare) + diceValue;
            return (nextIndex < DEFAULT_FINAL_SQUARES_SIZE) ? finalSquares.get(pawnColor).get(nextIndex) : null;
        }

        int nextSquareUnboundedIndex = (squares.indexOf(currentSquare) + diceValue);
        boolean hasPassedFinalSquare = false;
        int difference = 0;

        switch (pawnColor) {
            case GREEN -> {
                if (nextSquareUnboundedIndex > GREEN_END) {
                    hasPassedFinalSquare = true;
                    difference = nextSquareUnboundedIndex - GREEN_END;
                }
            }
            case BLUE -> {
                if (nextSquareUnboundedIndex > BLUE_END) {
                    hasPassedFinalSquare = true;
                    difference = nextSquareUnboundedIndex - BLUE_END;
                }
            }
        }
        if (hasPassedFinalSquare) {
            return finalSquares.get(pawnColor).get(difference - 1);
        } else {
            int boundedIndex = nextSquareUnboundedIndex % DEFAULT_SIZE;
            return squares.get(boundedIndex);
        }
    }
}

