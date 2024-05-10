package com.safaricom.fairflowappmicroservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.safaricom.fairflowappmicroservice.dtos.Organization.OrganizationRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    @JsonBackReference
    @OneToMany(mappedBy = "organization")
    private Set<Representative> representatives;

    public Organization(OrganizationRequestDto organizationDto) {
        this.name = organizationDto.getName();
        this.location = organizationDto.getLocation();
    }
}
