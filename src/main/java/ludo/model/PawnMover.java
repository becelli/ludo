package ludo.model;

import java.util.stream.Stream;

public class PawnMover {
  private final int redStart = 0;
  private final int greenStart = 14;
  private final int blueStart = 28;
  private final int yellowStart = 42;

  public PawnMover() {
  }

  private int getRelativeMoveCount(Pawn pawn) {
    int moveCount = pawn.getMoveCount();
    switch (pawn.getColor()) {
      case RED:
        return moveCount - this.redStart;
      case GREEN:
        return moveCount - this.greenStart;
      case BLUE:
        return moveCount - this.blueStart;
      case YELLOW:
        return moveCount - this.yellowStart;
      default:
        return -1;
    }
  }

  public boolean movePawn(Pawn[] allPawns, Pawn pawn, int steps) {
    if (!pawn.canMove(steps)) {
      return false;
    }

    this.movePawn(pawn, steps);

    if (pawn.isSafe()) {
      return true;
    }

    // If there is another pawn on the same position, send it back to base
    // WARNING: The moveCount is relative to the player and its origin
    // (e.g. redStart, greenStart, blueStart, yellowStart)
    Pawn[] enemyPawns = Stream.of(allPawns)
        .filter(otherPawn -> otherPawn.getColor() != pawn.getColor())
        .toArray(Pawn[]::new);

    for (Pawn enemyPawn : enemyPawns) {

      int movedPawnRelativeMoveCount = this.getRelativeMoveCount(pawn);
      int enemyPawnRelativeMoveCount = this.getRelativeMoveCount(enemyPawn);

      if (movedPawnRelativeMoveCount == enemyPawnRelativeMoveCount) {
        enemyPawn.setMoveCount(0);
      }
    }

    return true;
  }

  private void movePawn(Pawn pawn, int steps) {
    if (pawn.isAtBase()) {
      pawn.setMoveCount(1);
    } else {
      pawn.setMoveCount(pawn.getMoveCount() + steps);
    }
  }
}
