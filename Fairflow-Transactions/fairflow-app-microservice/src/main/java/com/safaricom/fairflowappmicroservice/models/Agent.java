package com.safaricom.fairflowappmicroservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.safaricom.fairflowappmicroservice.dtos.Agent.AgentRequestDto;
import com.safaricom.fairflowappmicroservice.models.enums.Gender;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String middleName;

    @Nullable
    private String email;
    private String phoneNumber;

    private LocalDate dob;
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @JsonBackReference
    @OneToMany(mappedBy = "agent")
    private Set<Beneficiary> beneficiaries;

    public Agent(AgentRequestDto agentDto) {
        this.firstName = agentDto.getFirstName();
        this.lastName = agentDto.getLastName();
        this.middleName = agentDto.getMiddleName();
        this.email = agentDto.getEmail();
        this.phoneNumber = agentDto.getPhoneNumber();
        this.dob = agentDto.getDob();
        this.gender = agentDto.getGender();
        this.address = agentDto.getAddress();
        this.beneficiaries = new HashSet<>();
    }

}
