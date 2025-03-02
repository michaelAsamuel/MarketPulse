package com.empiricism.marketpulse.investor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "investors")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=100)
    private String firstName;
    @Column(length=75)
    private String lastName;
    private String email;
    private String phoneNumber;
    @Column(length = 500)
    private String address;

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Temporal(TemporalType.DATE)
    private LocalDate dateOfAccountOpening;

    @Enumerated(EnumType.STRING)
    private KYCStatus kycStatus;

    @Enumerated(EnumType.STRING)
    private RiskToleranceLevel riskToleranceLevel;

    private Integer totalTrades;
    private BigDecimal averageTradeVolume;

    private BigDecimal netWorth;

    private Boolean emailNotifications;
    private Boolean smsNotifications;



}










