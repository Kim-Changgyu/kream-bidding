package com.example.kreambidding.model.bidding;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum BiddingStatus {
    UNDER_BIDDING("입찰 중"),
    ONGOING("진행 중"),
    TERMINATION("종료");

    private final String name;

    public static Optional<BiddingStatus> of(String name) {
        return Arrays.stream(BiddingStatus.values())
                .filter(biddingStatus -> biddingStatus.name.equals(name))
                .findFirst();
    }
}
