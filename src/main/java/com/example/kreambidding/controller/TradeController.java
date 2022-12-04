package com.example.kreambidding.controller;

import com.example.kreambidding.controller.dto.TradeDTO;
import com.example.kreambidding.model.trade.Trade;
import com.example.kreambidding.service.TradeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TradeController {
    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping("/api/v1/trades")
    public List<Trade> getTrades() {
        return tradeService.getTrades();
    }

    @PostMapping("/api/v1/trades")
    public Trade createTrade(TradeDTO tradeDTO) {
        return tradeService.createTrade(tradeDTO);
    }

    @PutMapping("/api/v1/trades")
    public Trade updateTrade(TradeDTO tradeDTO) {
        return tradeService.updateTrade(tradeDTO);
    }

    @DeleteMapping("/api/v1/trades/{id}")
    public void deleteTrade(@PathVariable("id") long id) {
        tradeService.deleteTrade(id);
    }
}
