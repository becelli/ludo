package ludo.model;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumMap;
import java.util.stream.Stream;

public class GameState implements Serializable {
  private static final long serialVersionUID = 1L;
  public Color currentTurnColor;
  public Pawn[] pawns = new Pawn[16];
  private Deque<Color> playerColors = new ArrayDeque<>();

  public GameState() {
    this.currentTurnColor = Color.RED;
    Stream.of(Color.values()).forEach(color -> {
      for (int i = 0; i < 4; i++) {
          this.pawns[color.ordinal() * 4 + i] = new Pawn(color);
      }
    });
  }

  public Color getWinner() {
    return Stream.of(Color.values())
        .filter(color -> {
          return Stream.of(this.pawns)
              .filter(pawn -> {
                return pawn.getColor().equals(color);
              })
              .allMatch(Pawn::isAtHome);
        })
        .findFirst()
        .orElse(null);
  }

  public void addPlayer(Color color) {
    this.playerColors.add(color);
  }

  public void nextTurn() {
    this.currentTurnColor = this.playerColors.poll();
    this.playerColors.add(this.currentTurnColor);
  }

  public boolean movePawn(Pawn pawn, int steps) {
    return PawnMover.movePawn(this.pawns, pawn, steps);
  }

  public Pawn[] getPawns() {
    return this.pawns;
  }

  // Object to EnumMap
  public EnumMap<Color, String[]> toMap() {
    EnumMap<Color, String[]> map = new EnumMap<>(Color.class);
    for (Color color : Color.values()) {
      String[] codes = new String[4];
      int baseCount = 4;
      for (int i = 0; i < 4; i++) {
        // if its B
        if (this.pawns[color.ordinal() * 4 + i].isAtBase()) {
          codes[i] = "B" + (baseCount--);
          continue;
        }
        codes[i] = this.pawns[color.ordinal() * 4 + i].getCode();
      }
      map.put(color, codes);
    }
    return map;
  }
}
