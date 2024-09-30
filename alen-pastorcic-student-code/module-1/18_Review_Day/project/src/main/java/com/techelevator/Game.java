package com.techelevator;

public abstract class Game {
    private String id;
    private String name;


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


}
