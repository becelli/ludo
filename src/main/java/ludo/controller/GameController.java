package ludo.controller;

import ludo.model.Dice;

public class GameController {
    public GameController() {}

    public int rollDice() {
        return Dice.roll();
    }
}
