package com.empiricism.marketpulse.trade;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeRequest {
    private static final Logger logger = LoggerFactory.getLogger(TradeRequest.class);

    @NotBlank(message = "Client is required")
    private Long clientId;

    @NotBlank(message = "Stock symbol is required")
    private String stockSymbol;

    @NotNull(message = "Order type is required")
    private OrderType orderType;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    private Double price; // Optional for market orders

    @NotNull(message = "Time in force is required")
    private TimeInForce timeInForce;

    @NotBlank(message = "Idempotency key is required")
    private String idempotencyKey;

    public void logTradeDetails() {
        logger.info("Trade Requested - Symbol: {}, Order Type: {}, Quantity: {}, Price: {}", stockSymbol, orderType, quantity, price);
    }
}
