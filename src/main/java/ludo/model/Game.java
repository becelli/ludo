package ludo.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Game {
  private Color currentTurnColor;
  private Pawn[] pawns = new Pawn[16];
  private Deque<Color> playerColors = new ArrayDeque<>();

  public Game() {
    this.currentTurnColor = Color.RED;
    for (Color color : Color.values()) {
      for (int i = 0; i < 4; i++) {
        this.pawns[color.ordinal() * 4 + i] = new Pawn(color);
      }
    }
  }

  public void addPlayer(Color color) {
    this.playerColors.add(color);
  }

  public void start() {
    this.currentTurnColor = this.playerColors.poll();
    this.playerColors.add(this.currentTurnColor);
  }
}
