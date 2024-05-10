package com.safaricom.fairflowappmicroservice.models;


import com.safaricom.fairflowappmicroservice.models.enums.City;
import com.safaricom.fairflowappmicroservice.models.enums.Region;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Region region;
    private City city;

    private int woreda;

    @Pattern(regexp = "^(.{1,90})$", message = "Invalid house number")
    private String houseNumber;
}
