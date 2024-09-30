package com.techelevator.farm;

import java.math.BigDecimal;

public class Egg implements Sellable {
    private BigDecimal price;

    public Egg() {
        price = new BigDecimal("0.25");
    }

    public String getName() {
        return "Egg";
    }

    public BigDecimal getPrice() {
        return price;
    }
}
