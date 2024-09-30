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
        System.out.println("The winner of the '"+ generalAuction.getItemForSale()+ "' is " + winningBid.getBidder() +
                            " with a bid of " + winningBid.getBidAmount());


        System.out.println();
        System.out.println("Starting a buyout auction");
        System.out.println("-----------------");

        BuyoutAuction buyoutAuction = new BuyoutAuction("Tech Elevator travel mug", 25);
        buyoutAuction.placeBid(new Bid("Natalie", 10));
        buyoutAuction.placeBid(new Bid("Bryan", 15));
        buyoutAuction.placeBid(new Bid("Shae", 13));
        buyoutAuction.placeBid(new Bid("Ben", 30));
        buyoutAuction.placeBid(new Bid("Chelsea", 40));
        winningBid = buyoutAuction.getHighBid();
        System.out.println("The winner of the '"+ buyoutAuction.getItemForSale()+ "' is " + winningBid.getBidder() +
                " with a bid of " + winningBid.getBidAmount());

//        Object object = new Object();
//        object.

        ReserveAuction reserveAuction = new ReserveAuction("Tech Elevator hat", 15);
        reserveAuction.placeBid(new Bid("Natalie", 10));
        reserveAuction.placeBid(new Bid("Bryan", 15));
        reserveAuction.placeBid(new Bid("Shae", 13));
        reserveAuction.placeBid(new Bid("Ben", 30));
        reserveAuction.placeBid(new Bid("Chelsea", 40));
        winningBid = reserveAuction.getHighBid();
        System.out.println("The winner of the '"+ reserveAuction.getItemForSale()+ "' is " + winningBid.getBidder() +
                " with a bid of " + winningBid.getBidAmount());
        System.out.println(reserveAuction);

        Auction auction1 = new BuyoutAuction("Ladder", 100);
        Auction auction2 = new Auction("Folding chair");
        Auction auction3 = new ReserveAuction("Wheelbarrow", 40);

        Auction[] auctions = new Auction[6];
        auctions[0] = generalAuction;
        auctions[1] = buyoutAuction;
        auctions[2] = reserveAuction;
        auctions[3] = auction1;
        auctions[4] = auction2;
        auctions[5] = auction3;

        Bid myBid = new Bid("Ben", 50);
        for (Auction auction : auctions) {
            auction.placeBid(myBid);
        }
    }
}
