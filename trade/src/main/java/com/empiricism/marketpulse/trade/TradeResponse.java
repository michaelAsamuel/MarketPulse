package com.empiricism.marketpulse.trade;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeResponse {
    private static final Logger logger = LoggerFactory.getLogger(TradeRequest.class);

    private Long id;

    private Long clientId;

    private String clientName;

    @NotBlank(message = "Stock symbol is required")
    private String stockSymbol;

    @NotNull(message = "Order type is required")
    private OrderType orderType;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    private Double price; // Optional for market orders

    private TimeInForce timeInForce;

    private LocalDateTime lastModifiedDate;

    private String idempotencyKey;
}
