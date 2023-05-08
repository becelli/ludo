package ludo.controller;

import java.net.InetAddress;
import java.util.ArrayList;

import ludo.model.Color;
import ludo.model.Game;
import ludo.model.GameState;
import ludo.model.Pawn;
import ludo.model.socket.Connection;
import ludo.view.MainWindow;

import javax.swing.*;

public class GameController {
    private final Game game = new Game();
    private final Connection connection = new Connection(this);
    private MainWindow UI;
    private boolean amIHost;
    private Color myColor;

    public GameController(MainWindow UI) {
        this.UI = UI;
    }

    public boolean joinGame(InetAddress address) {
        this.myColor = Color.YELLOW;
        try {
            this.connection.connectToHost(address, 5000);
            return true; // conncted successfully
        } catch (Exception e) {
            return false;
        }
    }

    // TODO: chamar da UI (para ser host)
    public void createGame() {
        game.createGame();
        this.myColor = Color.RED;
        this.connection.beAHost();
    }

    public int rollDice() {
        return this.game.rollDice();
    }

    public GameState getGameState() {
        return this.game.getGameState();
    }

    public Color getMyColor() {
        return this.myColor;
    }

    public ArrayList<String> getMovablePawns(int steps) {
        ArrayList<Pawn> pawns = this.game.getMovablePawns(this.myColor, steps);
        ArrayList<String> pawnsCode = new ArrayList<>();
        for (Pawn p : pawns) {
            pawnsCode.add(p.getCode());
        }
        return pawnsCode;
    }

    public void verifyIsThereAWinner() {
        if (this.game.isThereAWinner()) {
            Color winner = this.game.getWinner();
            if (winner.equals(this.myColor)) {
                JOptionPane.showMessageDialog(this.UI, "Você venceu!");
            } else {
                JOptionPane.showMessageDialog(this.UI, "Você ganhou... mais uma derrota!");
            }
            this.connection.disconnect();
            this.UI.disableForfeit();
            this.UI.lockDice();
            this.connection.setIsMyTurn(false);
            this.UI.enableConnectAgain();
        }
    }

    public void movePawn(int index, int steps) {
        ArrayList<Pawn> pawns = this.game.getMovablePawns(this.myColor, steps);
        Pawn pawn = pawns.get(index);
        this.game.movePawn(pawn, steps);
        this.UI.handlePawnSelectionResponse();
        this.sendGameState();
        this.verifyIsThereAWinner();
    }

    public void setGameState(GameState gameState) {
        if (gameState == null) {
            JOptionPane.showMessageDialog(this.UI, "O oponente desistiu!");
            this.connection.setIsMyTurn(false);
            this.UI.disableForfeit();
            this.UI.enableConnectAgain();
            this.UI.lockDice();
        } else {
            this.game.setGameState(gameState);
            this.UI.updateBoard();
            this.verifyIsThereAWinner();
        }
    }

    public void startGame() {
        Thread gameThread = new Thread(this.connection);
        gameThread.start();

        JOptionPane.showMessageDialog(this.UI, "O jogo começou!");
        this.UI.startGame();
    }

    public void freeDice() {
        this.UI.freeDice();
    }

    public void lockDice() {
        this.UI.lockDice();
    }

    public void connectToHost(InetAddress address, int port) throws Exception {
        this.connection.connectToHost(address, port);
    }

    public void forfeit() {
        this.game.setGameState(null);
        this.sendGameState();
        this.connection.setIsMyTurn(false);
        this.connection.disconnect();
        this.UI.lockDice();
        this.UI.disableForfeit();
        this.UI.enableConnectAgain();
    }

    public void sendGameState() {
        try {
            this.connection.sendGameState(this.getGameState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
