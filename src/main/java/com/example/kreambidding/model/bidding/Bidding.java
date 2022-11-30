package com.example.kreambidding.model.bidding;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "biddingId" })
public class Bidding {
    @NonNull
    private final long biddingId;
    @NonNull
    private final long productId;
    @NonNull
    private final long userId;
    @NonNull
    private final BiddingType biddingType;
    @NonNull
    private final long price;
    @NonNull
    private BiddingStatus biddingStatus;
}
