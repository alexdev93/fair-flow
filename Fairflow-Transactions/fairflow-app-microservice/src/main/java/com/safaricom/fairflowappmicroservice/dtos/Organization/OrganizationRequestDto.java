package com.safaricom.fairflowappmicroservice.dtos.Organization;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class OrganizationRequestDto {

    @NotNull
    @Pattern(regexp = "^.{1,300}$", message = "Invalid organization name, make sure it is less than 300 characters")
    private String name;

    @NotNull
    @Pattern(regexp = "^.{1,300}$", message = "Invalid location string, make sure it is less than 300 characters")
    private String location;
}
