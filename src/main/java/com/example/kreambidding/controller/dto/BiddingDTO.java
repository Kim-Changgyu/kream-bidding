package com.example.kreambidding.controller.dto;

import com.example.kreambidding.model.bidding.BiddingStatus;
import com.example.kreambidding.model.bidding.BiddingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BiddingDTO {
    private Long id;
    private long productId;
    private long userId;
    @NonNull
    private BiddingType biddingType;
    private long price;
    private BiddingStatus biddingStatus;
    private LocalDate contractedAt;
}
