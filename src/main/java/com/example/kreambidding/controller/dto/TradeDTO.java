package com.example.kreambidding.controller.dto;

import com.example.kreambidding.model.trade.TradeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TradeDTO {
    private Long id;
    private long buyBiddingId;
    private long sellBiddingId;
    private long price;
    private String invoiceNumber;
    private TradeStatus tradeStatus;
}
