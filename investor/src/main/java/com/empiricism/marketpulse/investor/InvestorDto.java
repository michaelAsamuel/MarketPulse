package com.empiricism.marketpulse.investor;

import java.time.LocalDate;

public record InvestorDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address,
        String accountNumber,
        String accountType,
        LocalDate dateOfAccountOpening,
        String kycStatus,
        String riskToleranceLevel,
        boolean emailNotifications,
        boolean smsNotifications


) {
}
