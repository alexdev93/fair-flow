package com.safaricom.fairflowappmicroservice.dtos.Agent;

import com.safaricom.fairflowappmicroservice.dtos.Beneficiary.BeneficiaryAgentResponse;
import com.safaricom.fairflowappmicroservice.models.Address;
import com.safaricom.fairflowappmicroservice.models.Agent;
import com.safaricom.fairflowappmicroservice.models.enums.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class AgentResponseDto {
    private Long id;

    private String firstName;
    private String lastName;
    private String middleName;

    private String email;
    private String phoneNumber;

    private LocalDate dob;
    private Gender gender;

    private Address address;

    private Set<BeneficiaryAgentResponse> beneficiaries;

    public AgentResponseDto(Agent agent) {
        this.id = agent.getId();
        this.firstName = agent.getFirstName();
        this.lastName = agent.getLastName();
        this.middleName = agent.getMiddleName();
        this.email = agent.getEmail();
        this.phoneNumber = agent.getPhoneNumber();
        this.dob = agent.getDob();
        this.gender = agent.getGender();
        this.address = agent.getAddress();
        this.beneficiaries = agent.getBeneficiaries().
                stream().
                map((b) -> new BeneficiaryAgentResponse(b)).
                collect(Collectors.toSet());
    }
}
