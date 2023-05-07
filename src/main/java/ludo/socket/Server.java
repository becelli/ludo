package ludo.socket;

import ludo.model.Game;
import ludo.model.GameState;

import java.io.*;
import java.net.*;
import java.util.Enumeration;

public class Server extends Thread{
    private ServerSocket serverSocket;
    private DataOutputStream out;
    private DataInputStream in;

    private Socket clientSocket;

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            this.serverSocket.setSoTimeout(100000);
            while (true) {
                this.clientSocket = serverSocket.accept();
                this.out = new DataOutputStream(clientSocket.getOutputStream());
                this.in = new DataInputStream(clientSocket.getInputStream());
                this.start();
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
                    if (!addr.isLinkLocalAddress() && !addr.isLoopbackAddress() && addr instanceof java.net.Inet4Address) {
                        return addr.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println("Error getting network interfaces: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void run() {
        try {
            out.writeBytes("Hello World");
            String data = in.readUTF();
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void sendGameState(Game game) {
        String msg = Encoder.toString(game.getGameState());
        try {
            out.writeBytes(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveGameState() {
        String data = null;
        try {
            data = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Game game = null;
        game.setGameState(Encoder.fromString(data));
    }
}
