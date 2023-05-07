package ludo.socket;

import ludo.model.Color;
import ludo.model.GameState;
import java.net.*;
import java.io.*;

public class Client {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client(InetAddress address, int port) {
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

    public void sendGameState(GameState gameState) {
        String game = Encoder.toString(gameState);
        try {
            out.writeBytes(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameState recieveGameState() {
        String data = null;
        try {
            data = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Encoder.fromString(data);
    }

    public Color recieveColor() {
        String data = null;
        try {
            data = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Color.valueOf(data);
    }
}
