package com.techelevator.farm;

import java.math.BigDecimal;

public class Cow extends FarmAnimal implements Sellable {

	private BigDecimal price;

	public Cow() {
		super("Cow", "moo!");
		price = new BigDecimal("1000.00");
	}

	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public String eat() {
		return "chews it up";
	}

}