package com.empiricism.marketpulse.trade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    boolean existsByIdempotencyKey(String idempotencyKey);
}
