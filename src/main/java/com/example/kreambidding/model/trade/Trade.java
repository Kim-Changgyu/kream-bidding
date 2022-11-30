package com.example.kreambidding.model.trade;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Trade {
    @EqualsAndHashCode.Include
    private final long tradeId;
    @NonNull
    private final long buyBiddingId;
    @NonNull
    private final long sellBiddingId;
    private String invoiceNumber;
    @NonNull
    private TradeStatus tradeStatus;
}