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
        /*System.out.println("Joining game...");
        this.amIHost = false;
        // Isso é bom pois se ele tentar conectar dá porta ocupada
        this.client = new Client(address, 5000);
        System.out.println("Connected to host.");
        this.myColor = this.client.recieveColor();
        this.game.setGameState(this.client.recieveGameState());
        System.out.println("Game joined.");*/
        // TESTE
        this.myColor = Color.GREEN;
        return true; // conncted successfully
    }

    // TODO: chamar da UI (para ser host)
    public void createGame() {
        game.createGame();
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

        if (this.game.getWinner().equals(this.myColor)) {
            JOptionPane.showMessageDialog(null, "Você venceu!");
            this.lockDice();
        } else if (this.game.getWinner() != null) {
            JOptionPane.showMessageDialog(null, "Você perdeu!");
            this.lockDice();
        } else {
            if (this.connection.getIsMyTurn()) {
//                this.UI.set
            } else {
//                this.UI.setTurn("Vez do oponente");
            }
        }
    }

    public void startGame() {
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
    
    public boolean isMyTurn() {
        return this.game.isMyTurn(this.myColor);
    }


}


