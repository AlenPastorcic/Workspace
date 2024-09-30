package com.techelevator.farm;

import java.math.BigDecimal;

public class Cow extends FarmAnimal implements Singable {

	private BigDecimal price;

	public Cow() {
        price = new BigDecimal("Cow", "mooooo");
		super("Cow", "moo!");
	}

	public BigDecimal getPrice() {
		return price;
	}

}