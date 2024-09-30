package com.techelevator.farm;

import java.math.BigDecimal;

public class Pig extends FarmAnimal {
    private BigDecimal price;
    private String name;
    private String sound;

    public Pig() {
        super("Pig", "oink");
        price = new BigDecimal(250.00);
        name = "Pig";
        sound = "oink";
    }
    public String getName() {
        return name;
    }
    public String getSound() {
        return sound;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
