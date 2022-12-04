package com.example.kreambidding.repository.trade;

import com.example.kreambidding.model.trade.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
