package ludo.model;

import java.io.Serializable;
import java.util.stream.Stream;

public class GameState implements Serializable {
  private static final long serialVersionUID = 1L;
  public Color currentTurnColor;
  public Pawn[] pawns = new Pawn[16];
  private Color winner;

  public GameState() {
    this.currentTurnColor = Color.RED;
    Stream.of(Color.values()).forEach(color -> {
      for (int i = 0; i < 4; i++) {
        this.pawns[color.ordinal() * 4 + i] = new Pawn(color);
      }
    });
  }

  public boolean verifyIfThereIsAWinner() {
    if (this.winner != null) {
      return true;
    }

    Color winner = Stream.of(Color.values())
        .filter(color -> {
          return Stream.of(this.pawns)
              .filter(pawn -> {
                return pawn.getColor() == color;
              })
              .allMatch(pawn -> {
                return pawn.isAtHome();
              });
        })
        .findFirst()
        .orElse(null);

    if (winner != null) {
      this.winner = winner;
      return true;
    }

    return false;
  }

  public Color getWinner() {
    return this.winner;
  }
}
