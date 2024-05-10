package com.safaricom.fairflowappmicroservice.dtos.Representative;

import com.safaricom.fairflowappmicroservice.dtos.Organization.OrganizationResponseDto;
import com.safaricom.fairflowappmicroservice.models.Organization;
import com.safaricom.fairflowappmicroservice.models.Representative;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class RepresentativeResponseDto {

    private Long id;

    private String firstName;
    private String lastName;
    private String middleName;

    private String email;
    private String phoneNumber;

    private double spentMoneyAmount;

    private OrganizationResponseDto organization;

    public RepresentativeResponseDto(Representative representative) {
        this.id = representative.getId();
        this.firstName = representative.getFirstName();
        this.lastName = representative.getLastName();
        this.middleName = representative.getMiddleName();
        this.email = representative.getEmail();
        this.phoneNumber = representative.getPhoneNumber();
        this.spentMoneyAmount = representative.getSpentMoneyAmount();
        this.organization = new OrganizationResponseDto(representative.getOrganization());
    }
}
