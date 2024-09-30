package com.techelevator;

public class SlotGame extends Game {

    private int chipDenomination = ONE_CHIPS;

    public SlotGame() {
        super(House.generateNewId("SL"));
    }

    public SlotGame(String id) {
        super(id);
    }

    public void setChipDenomination(int chipDenomination) {
        if (chipDenomination < ONE_CHIPS) {
            this.chipDenomination = ONE_CHIPS;
        }
        else if (chipDenomination > TEN_CHIPS) {
            this.chipDenomination = TEN_CHIPS;
        }
        else {
            this.chipDenomination = chipDenomination;
        }
    }

    public void feedChips() {
            inventory[chipDenomination]++;
    }

    @Override
    public String toString() {
        return getName() + ": a slot game where you are sure to have fun and win a lot";
    }

}
