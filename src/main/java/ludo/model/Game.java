package ludo.model;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Game {
  private GameState gameState;

  public void createGame() {
    this.gameState = new GameState();
  }

  public GameState getGameState() {
    return gameState;
  }

  public void setGameState(GameState gameState) {
    this.gameState = gameState;
  }

  public void addPlayer(Color color) {
    this.gameState.addPlayer(color);
  }

  public boolean isMyTurn(Color color) {
    return this.gameState.currentTurnColor.equals(color);
  }

  public boolean isThereAWinner() {
    return this.gameState.getWinner() != null;
  }

  public Color getWinner() {
    return this.gameState.getWinner();
  }

  public int rollDice() {
    // guardar quanto o cara rolou
    int rolledValue = Dice.roll();
    // TODO: REMOVE TESTE
    
    return rolledValue;
  }


  public boolean movePawn(Pawn pawn, int steps) {
    return this.gameState.movePawn(pawn, steps);
  }

  public ArrayList<Pawn> getMovablePawns(Color color, int steps) {
    return Stream.of(this.gameState.getPawns())
        .filter(pawn -> pawn.getColor().equals(color) && pawn.canMove(steps))
        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
  }
}
