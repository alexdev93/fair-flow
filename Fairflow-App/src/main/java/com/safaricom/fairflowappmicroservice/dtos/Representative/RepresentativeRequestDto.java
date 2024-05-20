package com.safaricom.fairflowappmicroservice.dtos.Representative;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
public class RepresentativeRequestDto implements Serializable {
    @NotNull
    @Pattern(regexp = "^\\w{1,30}$", message = "Invalid first name, make sure it is less than 30 characters")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^\\w{1,30}$", message = "Invalid last name, make sure it is less than 30 characters")
    private String lastName;

    @NotNull
    @Pattern(regexp = "^\\w{1,30}$", message = "Invalid middle name, make sure it is less than 30 characters")
    private String middleName;


    @Nullable
    @Email
    private String email;
    @NotNull
    @Pattern(regexp = "^(251|0)7\\d{8}$", message = "Invalid Safaricom phone number")
    private String phoneNumber;

    @NotNull
    private String password;

}