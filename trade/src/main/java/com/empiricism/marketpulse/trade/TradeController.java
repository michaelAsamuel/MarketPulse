package com.empiricism.marketpulse.trade;

import com.empiricism.marketpulse.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trades")
@RequiredArgsConstructor
class TradeController {
    private static final Logger logger = LoggerFactory.getLogger(TradeController.class);
    private final TradeService tradeService;


    @PostMapping("/place")
    public ResponseEntity<?> placeTrade(@RequestBody TradeRequest tradeRequest) {
        try {
            TradeResponse response = tradeService.placeOrder(tradeRequest);
            logger.info("Trade successfully placed for symbol: {} by Customer {}", tradeRequest.getStockSymbol(), response.getClientName());
            return ResponseEntity.ok(response);
        } catch (BusinessException e) {
            logger.error("Trade placement failed for symbol: {}, error: {}", tradeRequest.getStockSymbol(), e.getMessage());
            var errorResponse = Map.of("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }


    @GetMapping
    public ResponseEntity<List<TradeResponse>> findAll() {
        return ResponseEntity.ok(tradeService.findAll());
    }
}