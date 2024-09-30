package com.techelevator.farm;

public final class Cat extends FarmAnimal {

    public Cat() {
        super("Cat", "meow!");
    }

    /* Had to comment this out because we marked the parent's method as 'final'  */
//    @Override
//    public String getSound() {
//        if (isAsleep) {
//            return "purr!";
//        }
//        return super.getSound();
//    }

    @Override
    public String eat() {
        return "Eating cat food...";
    }
}
