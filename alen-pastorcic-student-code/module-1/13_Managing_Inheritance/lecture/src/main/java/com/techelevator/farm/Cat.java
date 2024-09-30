package com.techelevator.farm;

public class Cat extends FarmAnimal {

    public Cat() {
        super("Cat", "meow!");
    }

    @Override
    public String getSound() {
        if (isAsleep) {
            return "purr!";
        }
        return super.getSound();
    }
}
