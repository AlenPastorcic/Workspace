package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.model.Auction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private AuctionDao auctionDao;

    public AuctionController() {
        this.auctionDao = new MemoryAuctionDao();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Auction getAuction (@PathVariable int id) {
        return auctionDao.getAuctionById(id);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Auction addAuction(@RequestBody Auction newAuction) {
        Auction returnedAuction = auctionDao.createAuction(newAuction);
        return returnedAuction;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Auction> listAuctions (@RequestParam (value = "title_like", defaultValue = "") String title,
                                       @RequestParam (value = "currentBid_lte", defaultValue = "0") double currentBid) {
        if (!title.isEmpty() && currentBid > 0) {
            return auctionDao.getAuctionsByTitleAndMaxBid(title, currentBid);
        } else if (!title.isEmpty()) {
            return auctionDao.getAuctionsByTitle(title);
        } else if (currentBid > 0) {
            return auctionDao.getAuctionsByMaxBid(currentBid);
        } else {
            return auctionDao.getAuctions();
        }
    }

}
