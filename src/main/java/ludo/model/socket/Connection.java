package ludo.model.socket;


import ludo.controller.GameController;
import ludo.model.GameState;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection implements Runnable {
    public GameController controller;
    private InetAddress address;
    private int port;
    private Socket socket;
    private Thread threadHost;
    private boolean isMyTurn = false;

    public Connection(GameController controller) {
      this.controller = controller;
    }
    
    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public void setAddress(InetAddress ip) {
        this.address = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Waiting to receive game state...");
                this.recieveGameState();
                System.out.println("Game state received.");
                this.setIsMyTurn(true);
                System.out.println("My turn.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void startConnection() throws Exception {
            System.out.println("Starting connection...");
            this.setIsMyTurn(true);
            this.address = InetAddress.getLocalHost();
            ServerSocket tcpSocket = new ServerSocket(5000);
            this.port = tcpSocket.getLocalPort();
            this.socket = tcpSocket.accept();
            System.out.println("Connection established.");
            tcpSocket.close();
            System.out.println("Freeing dice...");
            this.controller.freeDice();
            this.controller.startGame();
    }

    public void sendGameState(GameState gameState) throws Exception {
        System.out.println("Sending game state...");
        this.setIsMyTurn(false);
        OutputStream outputStream = this.socket.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeObject(gameState);
        System.out.println("Game state sent.");
    }

    private void recieveGameState() throws Exception {
        ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());
        GameState gameState = (GameState) in.readObject();
        this.controller.setGameState(gameState);
        // Recebi o tabuleiro - é a minha vez - libera o dado
        this.controller.freeDice();
    }

    public void connectToHost(InetAddress address, int port) throws Exception {
        this.setIsMyTurn(false);
        this.socket = new Socket(address, port);
        this.controller.startGame();
    }


    public void disconnect() throws Exception {
        this.socket.close();
    }

    public void beAHost() {
        Host host = new Host(this);
        this.threadHost = new Thread(host);
        this.threadHost.start();
    }

    public void cancelHost() {
        this.threadHost.interrupt();
    }

    public void setIsMyTurn(boolean isMyTurn) {
        this.isMyTurn = isMyTurn;
    }

    public boolean getIsMyTurn() {
        return this.isMyTurn;
    }
}