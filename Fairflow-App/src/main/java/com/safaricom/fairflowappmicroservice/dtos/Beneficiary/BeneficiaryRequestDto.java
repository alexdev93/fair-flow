package com.safaricom.fairflowappmicroservice.dtos.Beneficiary;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.regex.RegExp;
import com.safaricom.fairflowappmicroservice.models.Address;
import com.safaricom.fairflowappmicroservice.models.enums.Gender;
import com.safaricom.fairflowappmicroservice.models.enums.MartialStatus;
import com.safaricom.fairflowappmicroservice.models.enums.Occupation;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class BeneficiaryRequestDto {
    @NotNull
    @Pattern(regexp = "^\\w{1,30}$", message = "Invalid first name, make sure it is less than 30 characters")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^\\w{1,30}$", message = "Invalid last name, make sure it is less than 30 characters")
    private String lastName;

    @NotNull
    @Pattern(regexp = "^\\w{1,30}$", message = "Invalid middle name, make sure it is less than 30 characters")
    private String middleName;

    @NotNull
    @Pattern(regexp = "^(251|0)7\\d{8}$", message = "Invalid Safaricom phone number")
    private String phoneNumber;

    @NotNull
    private Gender gender;

    @NotNull
    @Past(message = "Date of Birth must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotNull
    private Occupation occupation;

    @NotNull
    private MartialStatus martialStatus;

    @Nullable
    private int numberOfDependents;

    @NotNull
    private Address address;

    @NotNull
    private double registeredLongitude;

    @NotNull
    private double registeredLatitude;

    @Nullable
    @Pattern(regexp = "^.{1,255}$", message = "Invalid first name, make sure it is less than 255 characters")
    private String description;

}
