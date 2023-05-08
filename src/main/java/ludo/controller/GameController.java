package ludo.controller;

import java.net.InetAddress;

import ludo.model.Color;
import ludo.model.Game;
import ludo.model.GameState;
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true; // conncted successfully
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

    public void getMovablePawns(int steps) {
        // roll dice?
        System.out.println(this.game.getMovablePawns(this.myColor, steps));
    }
    public void setGameState(GameState gameState) throws Exception {
        if (gameState == null) {
            JOptionPane.showMessageDialog(null, "O oponente desistiu! Você venceu!");
//            this.UI.getConcede().setEnabled(false);
//            this.UI.getMenu().setEnabled(true);
//            this.UI.clearLog();
            this.connection.disconnect();
            this.connection.setIsMyTurn(false);
            return;
        }

        this.game.setGameState(gameState);

        Color winner = this.game.getWinner();
        if (winner != null) {
            if (winner.equals(this.myColor)) {
                JOptionPane.showMessageDialog(null, "Você venceu!");
            } else {
                JOptionPane.showMessageDialog(null, "Você perdeu!");
            }
            this.connection.disconnect();
            this.connection.setIsMyTurn(false);
            return;
        }
    }

    public void startGame() {
        System.out.println("Starting game...");
        Thread gameThread = new Thread(this.connection);
        gameThread.start();

        JOptionPane.showMessageDialog(null, "O jogo começou!");
        /* this.mf.setTitle("Damas - Em jogo");
        this.mf.getConcede().setEnabled(true);
        this.mf.getMenu().setEnabled(false);
        this.mf.setHost(false);
        this.mf.getCheckerBoard().rebuild(8, 8, 3);
        this.game.resetBoard(); */
        this.UI.startGame();
//        GameState state = this.game.getGameState();
//        if (this.connection.getIsMyTurn()) {
////            this.mf.setTurn("Sua vez!");
//        } else {
//            this.mf.setTurn("Vez do oponente");
//        }
//        this.mf.clearLog();
//        this.mf.getCheckerBoard().repaintBoard(m);
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


    public void sendGameState() {
        try {
            this.connection.sendGameState(this.getGameState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


