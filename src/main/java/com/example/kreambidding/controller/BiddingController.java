package com.example.kreambidding.controller;

import com.example.kreambidding.controller.dto.BiddingDTO;
import com.example.kreambidding.model.bidding.Bidding;
import com.example.kreambidding.service.BiddingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BiddingController {
    private final BiddingService biddingService;

    public BiddingController(BiddingService biddingService) {
        this.biddingService = biddingService;
    }

    @GetMapping("/api/v1/biddings")
    public List<Bidding> getBiddings() {
        return biddingService.getBiddings();
    }

    @GetMapping("/api/v1/biddings/{id}")
    public Bidding getBidding(@PathVariable("id") long id) {
        return biddingService.getBiddingById(id);
    }

    @GetMapping("/api/v1/biddings/product/{id}")
    public List<Bidding> getBiddingsByProduct(@PathVariable("id") long productId) {
        return biddingService.getBiddingsByProductId(productId);
    }

    @GetMapping("/api/v1/biddings/contracted/{id}")
    public List<Bidding> getContractedBiddings(@PathVariable("id") long productId) {
        return biddingService.getContractedBiddings(productId);
    }

    @GetMapping("/api/v1/biddings/user/{id}")
    public List<Bidding> getBiddingsByUser(@PathVariable("id") long userId) {
        return biddingService.getBiddingByUserId(userId);
    }

    @GetMapping("/api/v1/biddings/buy/{id}")
    public List<Bidding> getBuyBiddings(@PathVariable("id") long productId) {
        return biddingService.getBuyBiddings(productId);
    }

    @GetMapping("/api/v1/biddings/sell/{id}")
    public List<Bidding> getSellBiddings(@PathVariable("id") long productId) {
        return biddingService.getSellBiddings(productId);
    }

    @PostMapping("/api/v1/biddings")
    public Bidding createBidding(@RequestBody BiddingDTO biddingDTO) {
        return biddingService.createBidding(biddingDTO);
    }

    @PutMapping("/api/v1/biddings")
    public Bidding updateBidding(@RequestBody BiddingDTO biddingDTO) {
        return biddingService.updateBidding(biddingDTO);
    }

    @DeleteMapping("/api/v1/biddings/{id}")
    public void deleteBidding(@PathVariable("id") long id) {
        biddingService.deleteBidding(id);
    }
}
