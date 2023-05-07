package ludo.socket;

import ludo.model.GameState;
import java.net.*;
import java.io.*;
import java.util.Base64;

public class Client {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client(String address, int port) {
        try {
            this.socket = new Socket(address, port);
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        }
    }

    private void sendGameState(GameState gameState) {
        String msg = Encoder.toString(gameState);
        try {
            out.writeBytes(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GameState receiveGameState() {
        String data = null;
        try {
            data = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Encoder.fromString(data);
    }
}
