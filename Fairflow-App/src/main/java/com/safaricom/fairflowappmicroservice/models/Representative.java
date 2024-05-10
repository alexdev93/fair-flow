package com.safaricom.fairflowappmicroservice.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.safaricom.fairflowappmicroservice.dtos.Representative.RepresentativeRequestDto;
import com.safaricom.fairflowappmicroservice.models.enums.Gender;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Representative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String middleName;

    private String email;
    private String phoneNumber;

    private double spentMoneyAmount;

    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private Organization organization;

    public Representative(RepresentativeRequestDto representativeDto) {
        this.firstName = representativeDto.getFirstName();
        this.lastName = representativeDto.getLastName();
        this.middleName = representativeDto.getMiddleName();
        this.email = representativeDto.getEmail();
        this.phoneNumber = representativeDto.getPhoneNumber();
        this.spentMoneyAmount = 0;
    }
}
