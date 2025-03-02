package com.empiricism.marketpulse.investor;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
class InvestorMapper {

    Investor toInvestor(InvestorDto dto) {
        return Investor.builder()
                .id(dto.id())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .address(dto.address())
                .accountNumber(dto.accountNumber())
                .accountType(AccountType.valueOf(dto.accountType()))
                .kycStatus(KYCStatus.valueOf(dto.kycStatus()))
                .riskToleranceLevel(RiskToleranceLevel.valueOf(dto.riskToleranceLevel()))
                .emailNotifications(dto.emailNotifications())
                .smsNotifications(dto.smsNotifications())
                .build();
    }

    Investor updateInvestor(InvestorDto dto, Investor investor) {

        investor.setFirstName(dto.firstName());
        investor.setLastName(dto.lastName());
        investor.setEmail(dto.email());
        investor.setPhoneNumber(dto.phoneNumber());
        investor.setAddress(dto.address());
        investor.setAccountNumber(dto.accountNumber());
        investor.setAccountType(AccountType.valueOf(dto.accountType()));
        investor.setKycStatus(KYCStatus.valueOf(dto.kycStatus()));
        investor.setRiskToleranceLevel(RiskToleranceLevel.valueOf(dto.riskToleranceLevel()));
        investor.setEmailNotifications(dto.emailNotifications());
        investor.setSmsNotifications(dto.smsNotifications());
        return investor;
    }


    InvestorDto fromInvestor(Investor investor) {
        return new InvestorDto(
                investor.getId(),
                investor.getFirstName(),
                investor.getLastName(),
                investor.getEmail(),
                investor.getPhoneNumber(),
                investor.getAddress(),
                investor.getAccountNumber(),
                investor.getAccountType() != null ? investor.getAccountType().toString() : "INDIVIDUAL",
                investor.getDateOfAccountOpening() != null ? investor.getDateOfAccountOpening() : LocalDate.now(),
                investor.getKycStatus() != null ? investor.getKycStatus().toString() : "PENDING",
                investor.getRiskToleranceLevel() != null ? investor.getRiskToleranceLevel().toString() : "LOW",
                investor.getEmailNotifications() !=null ? investor.getEmailNotifications(): false,
                investor.getSmsNotifications() != null ? investor.getSmsNotifications() : false
        );
    }
}
