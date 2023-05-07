package ludo.socket;

import ludo.model.GameState;

import java.io.*;
import java.util.Base64;

public class Encoder {
    public static String toString(GameState game) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(game);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public static GameState fromString(String s) {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois;
        GameState game = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(data));
            game = (GameState) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return game;
    }
}
