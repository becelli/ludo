package ludo.model.socket;


import ludo.controller.GameController;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection implements Runnable {
    public GameController controller;
    private InetAddress address;
    private int port;
    private Socket socket;
    private ServerSocket tcpSocket;
    private Thread threadHost;

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
            this.recieveGameState();
        }
    }

    public void startConnection() {
        try {
            this.address = InetAddress.getLocalHost();
            this.tcpSocket = new ServerSocket(5000);
            this.port = this.tcpSocket.getLocalPort();
            this.socket = this.tcpSocket.accept();
            this.tcpSocket.close();
            // TODO: aaa
            this.controller.playerFound();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void sendBoard(GameState gameState) throws Exception {
        OutputStream outputStream = this.socket.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeObject(gameState);
    }

    private void recieveGameState() throws Exception {
        ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());
        GameState gameState = (GameState) in.readObject();
        this.controller.setMove(gameState);
    }

    public void connect() throws Exception {
        // TODO: aaa
        this.myTurn = false;
        this.socket = new Socket(address, port);
        // TODO: aaa
        this.controller.playerFound();
    }


    public void disconnect() throws Exception {
        this.socket.close();
    }

    public void host() {
        Host h = new Host(this);
        this.threadHost = new Thread(h);
        this.threadHost.start();
    }

    public void cancelHost() {
        this.threadHost.interrupt();
    }

    public void setMyTurn(boolean myTurn) {
        // TODO: aaa
        this.myTurn = myTurn;
    }
}