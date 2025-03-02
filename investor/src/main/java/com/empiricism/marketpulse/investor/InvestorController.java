package com.empiricism.marketpulse.investor;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/investor")
@RequiredArgsConstructor
public class InvestorController {

    private final InvestorService service;

    @PostMapping
    public ResponseEntity<InvestorDto> createInvestor(@RequestBody InvestorDto request) {
        InvestorDto createdInvestor = service.createInvestor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvestor);
    }
    
    @PutMapping
    public ResponseEntity<InvestorDto> updateInvestor(@RequestBody InvestorDto request) {
        return ResponseEntity.ok(service.createInvestor(request));
    }


    @GetMapping
    public ResponseEntity<List<InvestorDto>> findAll() {
        return ResponseEntity.ok(service.findAllInvestors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestorDto> findAll(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/em/{email}")
    public ResponseEntity<InvestorDto> findAll(@PathVariable("email") String req) {
        return ResponseEntity.ok(service.findByEmail(req));
    }


}
