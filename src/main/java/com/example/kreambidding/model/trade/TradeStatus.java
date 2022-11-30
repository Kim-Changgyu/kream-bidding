package com.example.kreambidding.model.trade;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum TradeStatus {
    BEFORE_PAYMENT("결제 전"),
    PAYMENT_CONFIRMED("결제 완료"),
    READY_FOR_DELIVERY("배송 준비"),
    SHIPPED("배송 중"),
    SETTLED("배송 완료"),
    CANCELED("취소");

    private final String name;

    public static Optional<TradeStatus> of(String name) {
        return Arrays.stream(TradeStatus.values())
                .filter(tradeStatus -> tradeStatus.name.equals(name))
                .findFirst();
    }
}
