package com.example.kreambidding.service;

import com.example.kreambidding.controller.dto.TradeDTO;
import com.example.kreambidding.model.trade.Trade;
import com.example.kreambidding.model.trade.TradeStatus;
import com.example.kreambidding.repository.trade.TradeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class TradeService {
    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Trade createTrade(TradeDTO tradeDTO) {
        Assert.isTrue(tradeDTO.getPrice() > 0L, "체결 금액은 최소 1 이상이어야 합니다.");

        Trade trade = new Trade();
        trade.setBuyBiddingId(tradeDTO.getBuyBiddingId());
        trade.setSellBiddingId(tradeDTO.getSellBiddingId());
        trade.setPrice(tradeDTO.getPrice());
        trade.setInvoiceNumber(tradeDTO.getInvoiceNumber());
        trade.setTradeStatus(TradeStatus.BEFORE_PAYMENT);

        return tradeRepository.save(trade);
    }

    public List<Trade> getTrades() {
        return tradeRepository.findAll();
    }

    public Trade updateTrade(TradeDTO tradeDTO) {
        Trade trade = tradeRepository.findById(tradeDTO.getId()).orElseThrow();
        trade.setBuyBiddingId(tradeDTO.getBuyBiddingId() != 0L ? tradeDTO.getBuyBiddingId() : trade.getBuyBiddingId());
        trade.setSellBiddingId(tradeDTO.getSellBiddingId() != 0L ? tradeDTO.getSellBiddingId() : trade.getSellBiddingId());
        trade.setInvoiceNumber(tradeDTO.getInvoiceNumber() != null ? tradeDTO.getInvoiceNumber() : trade.getInvoiceNumber());
        trade.setTradeStatus(tradeDTO.getTradeStatus() != null ? tradeDTO.getTradeStatus() : trade.getTradeStatus());

        return tradeRepository.save(trade);
    }

    public void deleteTrade(long id) {
        tradeRepository.deleteById(id);
    }
}
