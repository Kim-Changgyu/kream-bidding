package com.example.kreambidding.model.trade;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private long buyBiddingId;
    @Column(nullable = false)
    private long sellBiddingId;
    @Column(nullable = false)
    private long price;
    private String invoiceNumber;
    @Column(nullable = false)
    private TradeStatus tradeStatus;
}
