package com.empiricism.marketpulse.trade;

import com.empiricism.marketpulse.dto.InvestorDto;
import com.empiricism.marketpulse.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository tradeRepository;
    private final TradeMapper tradeMapper;
    private final RestTemplate restTemplate;


    public TradeResponse placeOrder(TradeRequest dto) {
        InvestorDto investor;
        if(tradeRepository.existsByIdempotencyKey(dto.getIdempotencyKey())){
            throw new BusinessException("Duplicate Request: " + dto.toString());
        }
        try {
            investor = getInvestor(dto.getClientId());
        } catch (HttpClientErrorException.BadRequest e) {
            throw new BusinessException("Investor validation failed: " + e.getResponseBodyAsString());
        }

        if (investor == null) {
            throw new BusinessException("Failed to validate investor with id: " + dto.getClientId());
        }

        Trade trade = tradeMapper.toTrade(dto);
        Trade savedTrade = tradeRepository.saveAndFlush(trade);

        return tradeMapper.fromTrade(savedTrade, investor.firstName()+" "+investor.lastName());
    }



//    public List<TradeResponse> findAll() {
//        List<Trade> trades = tradeRepository.findAll();
//        return trades.stream().map(tradeMapper::fromTrade).collect(Collectors.toList());
//    }


//        ResponseEntity<InvestorDto> response = restTemplate.getForEntity(
//                "http://localhost:8080/api/investor/{id}",
//                InvestorDto.class,
//                id
//        );
public InvestorDto getInvestor(Long id) {
        ResponseEntity<InvestorDto> response = restTemplate.getForEntity(
                "http://MPINVESTOR/api/investor/{id}",
                InvestorDto.class,
                id
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new BusinessException("Failed to validate investor with id: " + id);
        }
    }


    public List<TradeResponse> findAll() {
        List<Trade> trades = tradeRepository.findAll();
        return trades.stream().map(trade -> {
            InvestorDto investor = getInvestor(trade.getClientId());
            String investorName = investor != null ? investor.firstName() + " "+investor.lastName() : "Unknown Investor";
            return tradeMapper.fromTrade(trade, investorName);
        }).collect(Collectors.toList());
    }






}
