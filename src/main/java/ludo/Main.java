package ludo;

import java.net.InetAddress;

import ludo.controller.GameController;
import ludo.model.Dice;
import ludo.model.GameState;

public class Main {
    public static void main(String[] args) throws Exception {
        GameController gameController = new GameController();
        String input = "";
        while (!input.equals("exit")) {
            System.out.println("Enter command: ");
            input = System.console().readLine();
            if (input.equals("roll")) {
                System.out.println("Dice rolled: " + gameController.rollDice());
            } else if (input.equals("create")) {
                gameController.createGame();
            } else if (input.equals("join")) {
                input = System.console().readLine("Enter IP address: ");
                InetAddress address = InetAddress.getByName(input);
                gameController.joinGame(address);
            } else if (input.equals("state")) {
                GameState gameState = gameController.getGameState();
                System.out.println(gameState.toString());
            }
        }
    }
}
