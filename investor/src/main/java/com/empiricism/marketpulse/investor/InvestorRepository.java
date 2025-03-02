package com.empiricism.marketpulse.investor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface InvestorRepository extends JpaRepository<Investor, Long> {
    Optional<Investor> findByEmail(String email);
}
