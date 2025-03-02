package com.empiricism.marketpulse.trade;

import org.springframework.stereotype.Component;

@Component
public class TradeMapper {

    Trade toTrade(TradeRequest dto){

        return Trade.builder()
                .clientId(dto.getClientId())
                .stockSymbol(dto.getStockSymbol())
                .orderType(dto.getOrderType())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .timeInForce(dto.getTimeInForce())
                .idempotencyKey(dto.getIdempotencyKey())
                .build();
    }

    TradeResponse fromTrade(Trade trade, String custName){
        return TradeResponse.builder()
                .id(trade.getId())
                .clientId(trade.getClientId())
                .clientName(custName)
                .stockSymbol(trade.getStockSymbol())
                .quantity(trade.getQuantity())
                .price(trade.getPrice())
                .orderType(trade.getOrderType())
                .lastModifiedDate(trade.getCreatedDate())
                .timeInForce(trade.getTimeInForce())
                .idempotencyKey(trade.getIdempotencyKey())
                .build();
    }
}
