package ludo.model;

import java.io.Serializable;

public class Pawn implements Serializable {
    private final Color color;
    private int moveCount = 0;

    public Pawn(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean canMove(int steps) {
        if (this.isAtBase() && steps != 6) {
            return false;
        }

        return this.moveCount + steps <= 57;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    private boolean isInFinalPath() {
        return this.moveCount >= 52;
    }

    public boolean isAtHome() {
        return this.moveCount == 57;
    }

    public boolean isAtBase() {
        return this.moveCount == 0;
    }

    private boolean isOnStar() {
        return this.moveCount % 13 == 9; // 9, 22, 35, 48 (Position of star)
    }

    private boolean isInAFirstSquare() {
        return this.moveCount % 13 == 1; // 1, 14, 27, 40 (Initial position of safe zone)
    }

    public boolean isSafe() {
        return this.isInAFirstSquare() || this.isOnStar() || this.isInFinalPath() ||
                this.isAtHome() || this.isAtBase();
    }

    public String getCode() {
        if (this.isAtBase())
            return "B";
        if (this.isInFinalPath())
            return "F" + (this.moveCount - 51);
        int relativePosition;
        switch (this.color) {
            case GREEN -> {
                relativePosition = (this.moveCount + 13) % 52;
                return "N" + relativePosition;
            }
            case YELLOW -> {
                relativePosition = (this.moveCount + 26) % 52;
                return "N" + relativePosition;
            }
            case BLUE -> {
                relativePosition = (this.moveCount + 39) % 52;
                return "N" + relativePosition;
            }
            default -> {
                return "N" + this.moveCount;
            }
        }
    }
}
