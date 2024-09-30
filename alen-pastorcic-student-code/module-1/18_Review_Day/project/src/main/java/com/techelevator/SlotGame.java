package com.techelevator;

public class SlotGame extends Game {

    public SlotGame() {
        super(House.generateNewId("SL"));
    }

    public SlotGame(String id) {
        super(id);
    }

    public String toString() {
        return getName() + ": an exciting slot game where you are sure to have fun and win a lot";

    }
}
