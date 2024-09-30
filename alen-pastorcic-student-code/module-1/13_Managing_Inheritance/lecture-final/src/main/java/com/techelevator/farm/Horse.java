package com.techelevator.farm;

public class Horse extends FarmAnimal {

    public Horse() {
        super("Horse", "neigh!");
    }


    @Override
    public String eat() {
        return "Eating horse food...";
    }

}
