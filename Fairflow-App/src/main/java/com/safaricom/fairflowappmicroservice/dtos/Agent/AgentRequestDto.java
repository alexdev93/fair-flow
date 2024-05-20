package com.safaricom.fairflowappmicroservice.dtos.Agent;

import com.safaricom.fairflowappmicroservice.models.Address;
import com.safaricom.fairflowappmicroservice.models.enums.Gender;
import jakarta.annotation.Nullable;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AgentRequestDto {
    @NotNull
    @Pattern(regexp = "^\\w{1,30}$", message = "Invalid first name, make sure it is less than 30 characters")
    private String firstName;
    @NotNull
    @Pattern(regexp = "^\\w{1,30}$", message = "Invalid last name, make sure it is less than 30 characters")
    private String lastName;
    @NotNull
    @Pattern(regexp = "^\\w{1,30}$", message = "Invalid middle name, make sure it is less than 30 characters")
    private String middleName;

    @Email(message = "Invalid Email")
    private String email;
    @NotNull
    @Pattern(regexp = "^(251|0)7\\d{8}$", message = "Invalid Safaricom phone number")
    private String phoneNumber;

    @NotNull
    private String password;

    @NotNull
    @Past(message = "Date of Birth must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotNull
    private Gender gender;

    @NotNull
    private Address address;
}
