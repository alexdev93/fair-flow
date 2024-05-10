package com.safaricom.fairflowappmicroservice.services;

import com.safaricom.fairflowappmicroservice.dtos.Beneficiary.BeneficiaryRequestDto;
import com.safaricom.fairflowappmicroservice.exceptions.NotFoundException;
import com.safaricom.fairflowappmicroservice.models.Agent;
import com.safaricom.fairflowappmicroservice.models.Beneficiary;
import com.safaricom.fairflowappmicroservice.repositories.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;
    private final AgentService agentService;

    @Autowired
    public BeneficiaryService(BeneficiaryRepository beneficiaryRepository, AgentService agentService) {
        this.beneficiaryRepository = beneficiaryRepository;
        this.agentService = agentService;
    }
    public Beneficiary saveBeneficiary(BeneficiaryRequestDto beneficiaryDto, Long agentId) {

        Agent agent = agentService.getAgent(agentId);
        return beneficiaryRepository.save(new Beneficiary(beneficiaryDto, agent));
    }

    public Beneficiary getBeneficiary(Long beneficiaryId) {
        return beneficiaryRepository.findById(beneficiaryId).
                orElseThrow(() -> new NotFoundException("Beneficiary not found"));
    }

    public void deleteBeneficiary(Long beneficiaryId) {
        beneficiaryRepository.deleteById(beneficiaryId);
    }

    /*
    Optimize by creating a different update query that doesn't
    touch the agents relationship.
     */
    public Beneficiary updateBeneficiary(Long beneficiaryId, BeneficiaryRequestDto beneficiaryDto) {
        Beneficiary oldBeneficiary = getBeneficiary(beneficiaryId);
        Beneficiary beneficiary = new Beneficiary(beneficiaryDto, oldBeneficiary.getAgent());
        beneficiary.setId(beneficiaryId);
        return beneficiaryRepository.save(beneficiary);
    }

    public List<Beneficiary> listAllBeneficiaries() {
        return beneficiaryRepository.findAll();
    }

    public void receivePayment(Long beneficiaryId, double amount){
        Beneficiary beneficiary = getBeneficiary(beneficiaryId);
        beneficiary.setReceivedMoneyAmount(
                beneficiary.getReceivedMoneyAmount() + amount
        );
    }

}
