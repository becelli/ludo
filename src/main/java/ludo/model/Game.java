package ludo.model;

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

  public int rollDice() {
    return Dice.roll();
  }

  public void nextTurn() {
    this.gameState.nextTurn();
  }

  public boolean movePawn(Pawn pawn, int steps) {
    return this.gameState.movePawn(pawn, steps);
  }
}
