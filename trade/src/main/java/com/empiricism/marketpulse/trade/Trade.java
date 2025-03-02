package com.empiricism.marketpulse.trade;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "trades")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trade_seq_gen")
    private Long id;

    private Long clientId;

    @NotBlank(message = "Stock symbol is required")
    private String stockSymbol;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Order type is required")
    private OrderType orderType;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    private Double price; // Optional for market orders

    @Enumerated(EnumType.STRING)
//    @NotNull(message = "Time in force is required")
    private TimeInForce timeInForce;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    private String idempotencyKey;


}
