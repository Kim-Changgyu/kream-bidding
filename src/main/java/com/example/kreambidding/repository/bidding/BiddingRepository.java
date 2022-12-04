package com.example.kreambidding.repository.bidding;

import com.example.kreambidding.model.bidding.Bidding;
import com.example.kreambidding.model.bidding.BiddingStatus;
import com.example.kreambidding.model.bidding.BiddingType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BiddingRepository extends JpaRepository<Bidding, Long> {
    List<Bidding> findAllByProductId(long productId);
    List<Bidding> findAllByUserId(long userId);
    List<Bidding> findTopByProductIdAndBiddingTypeAndBiddingStatusOrderByPriceAscCreatedAtAsc(long productId, BiddingType biddingType, BiddingStatus biddingStatus);
    List<Bidding> findTopByProductIdAndBiddingTypeAndBiddingStatusOrderByPriceDescCreatedAtAsc(long productId, BiddingType biddingType, BiddingStatus biddingStatus);
    List<Bidding> findAllByProductIdAndBiddingStatusOrderByCreatedAtAsc(long productId, BiddingStatus biddingStatus);
    List<Bidding> findAllByProductIdAndBiddingTypeOrderByCreatedAtDesc(long productId, BiddingType biddingType);
    List<Bidding> findAllByProductIdAndUserIdAndBiddingStatus(long productId, long userId, BiddingStatus biddingStatus);
}
