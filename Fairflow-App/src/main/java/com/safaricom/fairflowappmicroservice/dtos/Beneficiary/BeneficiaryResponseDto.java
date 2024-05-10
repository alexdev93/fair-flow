package com.safaricom.fairflowappmicroservice.dtos.Beneficiary;

import com.safaricom.fairflowappmicroservice.dtos.Agent.AgentBeneficiaryResponse;
import com.safaricom.fairflowappmicroservice.dtos.Agent.AgentResponseDto;
import com.safaricom.fairflowappmicroservice.models.Address;
import com.safaricom.fairflowappmicroservice.models.Beneficiary;
import com.safaricom.fairflowappmicroservice.models.enums.Gender;
import com.safaricom.fairflowappmicroservice.models.enums.MartialStatus;
import com.safaricom.fairflowappmicroservice.models.enums.Occupation;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BeneficiaryResponseDto {
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

    private Address address;

    private double registeredLongitude;
    private double registeredLatitude;

    private String description;

    private AgentBeneficiaryResponse agent;

    public BeneficiaryResponseDto(Beneficiary beneficiary) {
        this.id = beneficiary.getId();
        this.firstName = beneficiary.getFirstName();
        this.lastName = beneficiary.getLastName();
        this.middleName = beneficiary.getMiddleName();
        this.phoneNumber = beneficiary.getPhoneNumber();
        this.receivedMoneyAmount = beneficiary.getReceivedMoneyAmount();
        this.gender = beneficiary.getGender();
        this.dob = beneficiary.getDob();
        this.occupation = beneficiary.getOccupation();
        this.martialStatus = beneficiary.getMartialStatus();
        this.numberOfDependents = beneficiary.getNumberOfDependents();
        this.address = beneficiary.getAddress();
        this.registeredLongitude = beneficiary.getRegisteredLongitude();
        this.registeredLatitude = beneficiary.getRegisteredLatitude();
        this.description = beneficiary.getDescription();
        this.agent = new AgentBeneficiaryResponse(beneficiary.getAgent());
    }
}
