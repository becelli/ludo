package ludo.model;

import java.io.Serializable;
import java.util.stream.Stream;

public class PawnMover implements Serializable {
  private static final int redStart = 1;
  private static final int greenStart = 14;
  private static final int yellowStart = 27;
  private static final int blueStart = 40;

  public PawnMover() {
  }

  private static int getRelativeMoveCount(Pawn pawn) {
    int moveCount = pawn.getMoveCount();
    switch (pawn.getColor()) {
      case RED:
        return (redStart + moveCount - 1) % 52;
      case GREEN:
        return (greenStart + moveCount - 1) % 52;
      case BLUE:
        return (blueStart + moveCount - 1) % 52;
      case YELLOW:
        return (yellowStart + moveCount - 1) % 52;
      default:
        return -1;
    }
  }

  public static boolean movePawn(Pawn[] allPawns, Pawn pawn, int steps) {
    if (!pawn.canMove(steps))
      return false;

    PawnMover.movePawn(pawn, steps);

    if (pawn.isSafe())
      return true;

    PawnMover.checkForCollision(allPawns, pawn);

    return true;
  }

  private static void checkForCollision(Pawn[] allPawns, Pawn pawn) {
    // If there is another pawn on the same position, send it back to base
    // WARNING: The moveCount is relative to the player and its origin
    // (e.g. redStart, greenStart, blueStart, yellowStart)
    Pawn[] enemyPawns = Stream.of(allPawns)
        .filter(
            enemy -> enemy.getColor() != pawn.getColor() && !enemy.isSafe())
        .toArray(Pawn[]::new);

    for (Pawn enemyPawn : enemyPawns) {

      int movedPawnRelativeMoveCount = PawnMover.getRelativeMoveCount(pawn);
      int enemyPawnRelativeMoveCount = PawnMover.getRelativeMoveCount(enemyPawn);

      if (movedPawnRelativeMoveCount == enemyPawnRelativeMoveCount) {
        enemyPawn.setMoveCount(0);
      }
    }
  }

  private static void movePawn(Pawn pawn, int steps) {
    if (pawn.isAtBase()) {
      pawn.setMoveCount(1);
    } else {
      pawn.setMoveCount(pawn.getMoveCount() + steps);
    }
  }
}
