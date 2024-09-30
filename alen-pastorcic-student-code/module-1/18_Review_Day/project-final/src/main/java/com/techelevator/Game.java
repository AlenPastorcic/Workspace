package com.techelevator;

public abstract class Game {
    private String id;
    private String name;
    protected static final int ONE_CHIPS = 0;
    protected static final int FIVE_CHIPS = 1;
    protected static final int TEN_CHIPS = 2;
    protected int[] inventory = new int[3];

    private String location = "unknown";

    public Game(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOneChips() {
        return inventory[ONE_CHIPS];
    }

    public void setOneChips(int chips) {
        inventory[ONE_CHIPS] = chips;
    }

    public int getFiveChips() {
        return inventory[FIVE_CHIPS];
    }

    public void setFiveChips(int chips) {
        inventory[FIVE_CHIPS] = chips;
    }

    public int getTenChips() {
        return inventory[TEN_CHIPS];
    }

    public void setTenChips(int chips) {
        inventory[TEN_CHIPS] = chips;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return getName() + ": a casino game";
    }

}
