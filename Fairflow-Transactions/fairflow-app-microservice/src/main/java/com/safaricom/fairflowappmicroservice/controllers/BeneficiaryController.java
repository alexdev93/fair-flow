package com.safaricom.fairflowappmicroservice.controllers;


import com.safaricom.fairflowappmicroservice.dtos.Beneficiary.BeneficiaryResponseDto;
import com.safaricom.fairflowappmicroservice.dtos.Beneficiary.BeneficiaryRequestDto;
import com.safaricom.fairflowappmicroservice.services.BeneficiaryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/api/v1/beneficiaries")
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    @Autowired
    public BeneficiaryController(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    @GetMapping
    public ResponseEntity<List<BeneficiaryResponseDto>> getBeneficiaries() {
        return new ResponseEntity<>(
                beneficiaryService.listAllBeneficiaries().
                        stream().
                        map((beneficiary) -> new BeneficiaryResponseDto(beneficiary)).
                        collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{beneficiaryId}")
    public ResponseEntity<BeneficiaryResponseDto> getBeneficiary(@PathVariable Long beneficiaryId) {
        return new ResponseEntity<>(
                new BeneficiaryResponseDto(beneficiaryService.getBeneficiary(beneficiaryId)),
                HttpStatus.OK);
    }

    @PostMapping( "/{agentId}")
    public ResponseEntity<BeneficiaryResponseDto> saveBeneficiary(@Valid @RequestBody BeneficiaryRequestDto beneficiaryDto,
                                                                  @PathVariable Long agentId) {
        return new ResponseEntity<>(
                new BeneficiaryResponseDto(beneficiaryService.saveBeneficiary(beneficiaryDto, agentId)),
                HttpStatus.CREATED);
    }

    @PutMapping("/{beneficiaryId}")
    public ResponseEntity<BeneficiaryResponseDto> updateBeneficiary(@PathVariable Long beneficiaryId,
                                                                    @Valid @RequestBody BeneficiaryRequestDto beneficiaryDto) {
        return new ResponseEntity<>(
                new BeneficiaryResponseDto(beneficiaryService.updateBeneficiary(beneficiaryId, beneficiaryDto)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{beneficiaryId}")
    public ResponseEntity<HttpStatus> deleteBeneficiary(@PathVariable Long beneficiaryId) {
        beneficiaryService.deleteBeneficiary(beneficiaryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
