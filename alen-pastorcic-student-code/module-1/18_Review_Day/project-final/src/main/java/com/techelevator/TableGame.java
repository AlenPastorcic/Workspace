package com.techelevator;

public class TableGame extends Game {

    public TableGame() {
        super(House.generateNewId("TB"));
    }

    public TableGame(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return getName() + ": an exciting table game";
    }
}
