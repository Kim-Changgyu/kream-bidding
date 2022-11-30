package com.example.kreambidding.model.bidding;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum BiddingType {
    BUY("구매"),
    SELL("판매");

    private final String name;

    public static Optional<BiddingType> of(String name) {
        return Arrays.stream(BiddingType.values())
                .filter(biddingType -> biddingType.name.equals(name))
                .findFirst();
    }
}
