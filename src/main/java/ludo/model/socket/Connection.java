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
                this.receiveGameState();
                this.setIsMyTurn(true);
            } catch (Exception e) {
                this.disconnect();
                break;
            }
        }
    }

    public void startConnection() {
        try {
            this.setIsMyTurn(true);
            this.address = InetAddress.getLocalHost();
            ServerSocket tcpSocket = new ServerSocket(5000);
            this.port = tcpSocket.getLocalPort();
            this.socket = tcpSocket.accept();
            tcpSocket.close();
            this.controller.freeDice();
            this.controller.startGame();
        } catch (Exception e) {
            System.out.println("Error while starting connection.");
        }
    }

    public void sendGameState(GameState gameState) {
        try {
            this.setIsMyTurn(false);
            OutputStream outputStream = this.socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(gameState);
        } catch (Exception e) {
            System.out.println("Error while sending game state.");
        }
    }

    private void receiveGameState() throws IOException {
        try {
            ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());
            GameState gameState = (GameState) in.readObject();
            this.controller.setGameState(gameState);
        } catch (ClassNotFoundException e) {
            System.out.println("Error while receiving game state.");
        }
    }

    public void connectToHost(InetAddress address, int port) throws IOException {
        this.setIsMyTurn(false);
        this.socket = new Socket(address, port);
        this.controller.startGame();
    }

    public void disconnect() {
        try {
            this.socket.close();
        } catch (IOException e) {
            System.out.println("Error while closing socket.");
        }
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
