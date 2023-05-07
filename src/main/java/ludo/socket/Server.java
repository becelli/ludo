package ludo.socket;

import ludo.model.Color;
import ludo.model.GameState;

import java.io.*;
import java.net.*;
import java.util.Enumeration;

public class Server {
    private ServerSocket serverSocket;
    private DataOutputStream out;
    private DataInputStream in;

    private Socket clientSocket;

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            while (true) {
                this.clientSocket = serverSocket.accept();
                this.out = new DataOutputStream(clientSocket.getOutputStream());
                this.in = new DataInputStream(clientSocket.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getNetworkIP() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (!addr.isLinkLocalAddress() && !addr.isLoopbackAddress()
                            && addr instanceof java.net.Inet4Address) {
                        return addr.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println("Error getting network interfaces: " + e.getMessage());
        }
        return null;
    }

    public void sendGameState(GameState gameState) {
        String gameStateString = Encoder.toString(gameState);
        try {
            out.writeBytes(gameStateString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameState recieveGameState() {
        String gameStateStringBytes = null;
        try {
            gameStateStringBytes = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Encoder.fromString(gameStateStringBytes);
    }

    public void sendColor(Color color) {
        try {
            out.writeBytes(color.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
