package com.safaricom.fairflowappmicroservice.services;

import com.safaricom.fairflowappmicroservice.dtos.Payment.PaymentRequestDto;
import com.safaricom.fairflowappmicroservice.dtos.Representative.RepresentativeRequestDto;
import com.safaricom.fairflowappmicroservice.dtos.User.UserCreate;
import com.safaricom.fairflowappmicroservice.exceptions.NotFoundException;
import com.safaricom.fairflowappmicroservice.models.Organization;
import com.safaricom.fairflowappmicroservice.models.Representative;
import com.safaricom.fairflowappmicroservice.repositories.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class RepresentativeService {

    private final RepresentativeRepository representativeRepository;
    private final BeneficiaryService beneficiaryService;
    private final OrganizationService organizationService;

    @Autowired
    private WebClient webClient;

    @Autowired
    public RepresentativeService(RepresentativeRepository representativeRepository, BeneficiaryService beneficiaryService, OrganizationService organizationService) {
        this.representativeRepository = representativeRepository;
        this.beneficiaryService = beneficiaryService;
        this.organizationService = organizationService;
    }

    public Representative getRepresentative(Long representativeId) {
        return representativeRepository.findById(representativeId).
                orElseThrow(() -> new NotFoundException("Representative not found"));
    }

    public Representative saveRepresentative(RepresentativeRequestDto representativeDto, Long organizationId) {

        UserCreate userCreate = new UserCreate(representativeDto);
        webClient.post()
                .uri("http://USER-SERVICE/api/v1/users")
                .bodyValue(userCreate).retrieve()
                .bodyToMono(UserCreate.class)
                .block();

        Organization organization = organizationService.getOrganization(organizationId);
        Representative representative = new Representative(representativeDto);
        representative.setOrganization(organization);


        return representativeRepository.save(representative);
    }

    public Representative updateRepresentative(Long representativeId, RepresentativeRequestDto representativeDto) {
        Representative representative = new Representative(representativeDto);
        representative.setId(representativeId);
        return representativeRepository.save(representative);
    }

    public List<Representative> listAllRepresentatives() {
        return representativeRepository.findAll();
    }

    public void deleteRepresentative(Long representativeId) {
        representativeRepository.deleteById(representativeId);
    }

    public void transferPayment(PaymentRequestDto paymentDto){
        beneficiaryService.receivePayment(paymentDto.getBeneficiaryId(), paymentDto.getMonetaryAmount());
        Representative representative = getRepresentative(paymentDto.getRepresentativeId());
        representative.setSpentMoneyAmount(representative.getSpentMoneyAmount() + paymentDto.getMonetaryAmount());
        representativeRepository.save(representative);
    }

}
