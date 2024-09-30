package com.techelevator;

public class ReserveAuction extends Auction {

    private int reservePrice;

    public ReserveAuction(String itemForSale, reservePrice) {
        super(itemForSale);
        this.reservePrice = reservePrice;
    }

    public boolean placeBid(Bid offerBid) {
        boolean isCurrentWinningBid = false;

        if (offerBid.getBidAmount() >= reservePrice) {

        }
        return isCurrentWinningBid;
    }

    public int getReservePrice() {
        return reservePrice;
    }

    @Override
    public String toString() {
        return "Auction of '" + getItemForSale() + "' with reserve price of " + getReservePrice() + ".";
    }
}
