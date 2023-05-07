package ludo.controller;

import java.net.InetAddress;

import ludo.model.Color;
import ludo.model.Dice;
import ludo.model.Game;
import ludo.model.GameState;
import ludo.socket.Client;
import ludo.socket.Server;

public class GameController {
    private Game game = new Game();
    private boolean amIHost;
    private Server server;
    private Client client;
    private Color myColor;

    public void joinGame(InetAddress address) {
        this.amIHost = false;
        this.client = new Client(address, 5000);
        this.myColor = this.client.recieveColor();
        this.game.setGameState(this.client.recieveGameState());
    }

    public void createGame() {
        game.createGame();
        this.amIHost = true;
        this.server = new Server(5000);
        this.myColor = Color.RED;
        this.server.sendColor(Color.YELLOW);
        this.server.sendGameState(this.game.getGameState());
    }

    public int rollDice() {
        return Dice.roll();
    }

    public GameState getGameState() {
        return this.game.getGameState();
    }

}
