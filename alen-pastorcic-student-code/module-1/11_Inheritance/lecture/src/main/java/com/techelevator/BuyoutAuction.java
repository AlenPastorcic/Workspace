package com.techelevator;

public class BuyoutAuction extends Auction {

    private  int buyoutPrice;

    public BuyoutAuction(String itemForSale, int buyoutPrice) {
        super(itemForSale);
        this.buyoutPrice = buyoutPrice;
    }

    public boolean placeBid(Bid offerBid) {
        boolean isCurrentWinningBid = false;
        if (getHighBid().getBidAmount() < buyoutPrice) {

            if (offerBid.getBidAmount() >= buyoutPrice) {
                offerBid = new Bid(offerBid.getBidder(), buyoutPrice);
            }

            isCurrentWinningBid = super.placeBid(offerBid);

        }
        return isCurrentWinningBid;
    }
    public int getBuyoutPrice() {
        return buyoutPrice;
    }
}
