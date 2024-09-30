package com.techelevator;

public class Application {

    public static void main(String[] args) {

        // Create a new general auction
        System.out.println("Starting a general auction");
        System.out.println("-----------------");

        Auction generalAuction = new Auction("Tech Elevator t-shirt");

        generalAuction.placeBid(new Bid("Josh", 1));
        generalAuction.placeBid(new Bid("Fonz", 23));
        generalAuction.placeBid(new Bid("Rick Astley", 13));
        //....
        //....
        // This might go on until the auction runs out of time or hits a max # of bids
        Bid winningBid = generalAuction.getHighBid();
        System.out.println("The winner of the '" + generalAuction.getItemForSale() + " is " + winningBid.getBidder() +
                            " with a bid of " +winningBid.getBidAmount());


        System.out.println();
        System.out.println("Starting a buyout auction");
        System.out.println("---------------");

        BuyoutAuction buyoutAuction = new BuyoutAuction("Tech Elevator travel mug", 25);
        buyoutAuction.placeBid(new Bid("Natalie", 10));
        buyoutAuction.placeBid(new Bid("Bryan", 15) );
        buyoutAuction.placeBid(new Bid("Shae", 13));
        buyoutAuction.placeBid(new Bid("Ben", 30));
        buyoutAuction.placeBid(new Bid("Chelsea", 40));
        winningBid = buyoutAuction.getHighBid();
        System.out.println("The winner of the '" + generalAuction.getItemForSale() + " is " + winningBid.getBidder() +
                " with a bid of " +winningBid.getBidAmount());

        //Object object = new Object();
        //object.


        ReserveAuction reserveAuction = new ReserveAuction("Tech Elevator hat", 15);
    }

}
