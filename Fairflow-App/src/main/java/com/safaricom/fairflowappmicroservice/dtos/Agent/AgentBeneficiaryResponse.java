package com.safaricom.fairflowappmicroservice.dtos.Agent;

import com.safaricom.fairflowappmicroservice.models.Address;
import com.safaricom.fairflowappmicroservice.models.Agent;
import com.safaricom.fairflowappmicroservice.models.enums.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Data
public class AgentBeneficiaryResponse {
    private Long id;

    private String firstName;
    private String lastName;
    private String middleName;

    private String email;
    private String phoneNumber;

    private LocalDate dob;
    private Gender gender;

    private Address address;

    public AgentBeneficiaryResponse(Agent agent) {
        this.id = agent.getId();
        this.firstName = agent.getFirstName();
        this.lastName = agent.getLastName();
        this.middleName = agent.getMiddleName();
        this.email = agent.getEmail();
        this.phoneNumber = agent.getPhoneNumber();
        this.dob = agent.getDob();
        this.gender = agent.getGender();
        this.address = agent.getAddress();

    }
}
