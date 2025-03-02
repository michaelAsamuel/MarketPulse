package com.empiricism.marketpulse.investor;


import com.empiricism.marketpulse.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvestorService {
    private final InvestorRepository investorRepository;
    private final InvestorMapper mapper;

    InvestorDto createInvestor(InvestorDto investorDto) {
        Investor investor = new Investor();
        if (investorDto.id() != null) {
            if (investorRepository.existsById(investorDto.id())) {
                investor = investorRepository.findById(investorDto.id()).get();
                investor = mapper.updateInvestor(investorDto, investor);
                investor = investorRepository.save(investor);

            }else{
                throw new BusinessException("Investor does not exist!");
            }
        } else {
            investor = investorRepository.save(mapper.toInvestor(investorDto));
        }
        return mapper.fromInvestor(investor);

    }


    public List<InvestorDto> findAllInvestors() {
        return investorRepository.findAll()
                .stream()
                .map(mapper::fromInvestor)
                .collect(Collectors.toList());
    }

    public InvestorDto findByEmail(String email){
        Investor investor = investorRepository.findByEmail(email).get();
         return mapper.fromInvestor(investor);
    }

    public InvestorDto findById(Long id) {
        if (investorRepository.existsById(id)) {
            Investor investor = investorRepository.findById(id).get();
            return mapper.fromInvestor(investor);
        } else {
            throw new BusinessException(" Investor with Id does not exist");
        }
    }
        public InvestorDto getById(Long id) {
        var investor = investorRepository.findById(id).
                orElseThrow(() -> new BusinessException("Investor with Id: "+ id + " does not exist!"));
        return mapper.fromInvestor(investor);



    }
}
