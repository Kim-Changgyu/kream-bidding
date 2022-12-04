package com.example.kreambidding.service;

import com.example.kreambidding.controller.dto.BiddingDTO;
import com.example.kreambidding.controller.dto.TradeDTO;
import com.example.kreambidding.model.bidding.Bidding;
import com.example.kreambidding.model.bidding.BiddingStatus;
import com.example.kreambidding.model.bidding.BiddingType;
import com.example.kreambidding.repository.bidding.BiddingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class BiddingService {
    private final BiddingRepository biddingRepository;
    private final TradeService tradeService;

    public BiddingService(BiddingRepository biddingRepository, TradeService tradeService) {
        this.biddingRepository = biddingRepository;
        this.tradeService = tradeService;
    }

    public Bidding createBidding(BiddingDTO biddingDTO) {
        Assert.isTrue(biddingDTO.getPrice() > 0L, "입찰 금액은 최소 1 이상이어야 합니다.");

        Bidding bidding = new Bidding();
        bidding.setProductId(biddingDTO.getProductId());
        bidding.setUserId(biddingDTO.getUserId());
        bidding.setBiddingType(biddingDTO.getBiddingType());
        bidding.setPrice(biddingDTO.getPrice());
        bidding.setBiddingStatus(BiddingStatus.UNDER_BIDDING);
        bidding.setCreatedAt(LocalDate.now());

        // 해당 상품에 대해 이미 입찰이 존재하는지 체크
        if (hasAlreadyExistsBidding(bidding.getProductId(), bidding.getUserId())) {
            throw new RuntimeException("이미 해당 상품에 대한 입찰이 존재합니다.");
        }

        bidding = biddingRepository.save(bidding);

        // 자동 체결 (구매, 판매 금액이 일치한 경우)
        List<Bidding> targetBidding;
        if (bidding.getBiddingType() == BiddingType.BUY) {
            targetBidding = biddingRepository.findTopByProductIdAndBiddingTypeAndBiddingStatusOrderByPriceAscCreatedAtAsc(bidding.getProductId(), BiddingType.SELL, BiddingStatus.UNDER_BIDDING);
        } else {
            targetBidding = biddingRepository.findTopByProductIdAndBiddingTypeAndBiddingStatusOrderByPriceDescCreatedAtAsc(bidding.getProductId(), BiddingType.BUY, BiddingStatus.UNDER_BIDDING);
        }
        if(!targetBidding.isEmpty() && bidding.getPrice() == targetBidding.get(0).getPrice()) {
            bidding.setBiddingStatus(BiddingStatus.ONGOING);
            bidding.setCreatedAt(LocalDate.now());
            targetBidding.get(0).setBiddingStatus(BiddingStatus.ONGOING);
            targetBidding.get(0).setCreatedAt(LocalDate.now());
            biddingRepository.save(targetBidding.get(0));

            if (bidding.getBiddingType() == BiddingType.BUY) {
                tradeService.createTrade(new TradeDTO(null, bidding.getId(), targetBidding.get(0).getId(), bidding.getPrice(), null, null));
            } else {
                tradeService.createTrade(new TradeDTO(null, targetBidding.get(0).getId(), bidding.getId(), bidding.getPrice(), null, null));
            }
        }

        return bidding;
    }

    public List<Bidding> getBiddings() {
        return biddingRepository.findAll();
    }

    public Bidding getBiddingById(long id) {
        return biddingRepository.findById(id).orElseThrow();
    }

    public List<Bidding> getBiddingsByProductId(long productId) {
        return biddingRepository.findAllByProductId(productId);
    }

    public List<Bidding> getBiddingByUserId(long userId) {
        return biddingRepository.findAllByUserId(userId);
    }

    public List<Bidding> getContractedBiddings(long productId) {
        return biddingRepository.findAllByProductIdAndBiddingStatusOrderByCreatedAtAsc(productId, BiddingStatus.TERMINATION);
    }

    public List<Bidding> getBuyBiddings(long productId) {
        return biddingRepository.findAllByProductIdAndBiddingTypeOrderByCreatedAtDesc(productId, BiddingType.BUY);
    }

    public List<Bidding> getSellBiddings(long productId) {
        return biddingRepository.findAllByProductIdAndBiddingTypeOrderByCreatedAtDesc(productId, BiddingType.SELL);
    }

    public Bidding updateBidding(BiddingDTO biddingDTO) {
        Bidding bidding = biddingRepository.findById(biddingDTO.getId()).orElseThrow();
        bidding.setBiddingType(biddingDTO.getBiddingType() != null ? biddingDTO.getBiddingType() : bidding.getBiddingType());
        bidding.setBiddingStatus(biddingDTO.getBiddingStatus() != null ? biddingDTO.getBiddingStatus() : bidding.getBiddingStatus());
        bidding.setPrice(biddingDTO.getPrice() != 0L ? biddingDTO.getPrice() : bidding.getPrice());

        return biddingRepository.save(bidding);
    }

    public void deleteBidding(long id) {
        biddingRepository.deleteById(id);
    }

    private boolean hasAlreadyExistsBidding(long productId, long userId) {
        return !biddingRepository.findAllByProductIdAndUserIdAndBiddingStatus(productId, userId, BiddingStatus.UNDER_BIDDING).isEmpty();
    }
}
