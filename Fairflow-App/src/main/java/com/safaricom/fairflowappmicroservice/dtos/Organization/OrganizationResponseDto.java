package com.safaricom.fairflowappmicroservice.dtos.Organization;

import com.safaricom.fairflowappmicroservice.models.Organization;
import lombok.Data;

@Data
public class OrganizationResponseDto {

    private Long id;
    private String name;
    private String location;

    public OrganizationResponseDto(Organization organization) {
        this.id = organization.getId();
        this.name = organization.getName();
        this.location = organization.getLocation();
    }
}
