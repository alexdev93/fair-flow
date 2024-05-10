package com.safaricom.fairflowappmicroservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.safaricom.fairflowappmicroservice.dtos.Beneficiary.BeneficiaryRequestDto;
import com.safaricom.fairflowappmicroservice.models.enums.Gender;
import com.safaricom.fairflowappmicroservice.models.enums.MartialStatus;
import com.safaricom.fairflowappmicroservice.models.enums.Occupation;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String middleName;

    private String phoneNumber;
    private double receivedMoneyAmount;

    private Gender gender;
    private LocalDate dob;
    private Occupation occupation;
    private MartialStatus martialStatus;
    private int numberOfDependents;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private double registeredLongitude;
    private double registeredLatitude;

    private String description;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn
    private Agent agent;

    public Beneficiary(BeneficiaryRequestDto beneficiaryDto, Agent agent) {

        firstName = beneficiaryDto.getFirstName();
        lastName = beneficiaryDto.getLastName();
        middleName = beneficiaryDto.getMiddleName();
        phoneNumber = beneficiaryDto.getPhoneNumber();

        receivedMoneyAmount = 0;

        gender = beneficiaryDto.getGender();
        dob = beneficiaryDto.getDob();
        occupation = beneficiaryDto.getOccupation();
        martialStatus = beneficiaryDto.getMartialStatus();
        numberOfDependents = beneficiaryDto.getNumberOfDependents();

        address = beneficiaryDto.getAddress();
        registeredLongitude = beneficiaryDto.getRegisteredLongitude();
        registeredLatitude = beneficiaryDto.getRegisteredLatitude();

        description = beneficiaryDto.getDescription();
        this.agent = agent;
    }
}
