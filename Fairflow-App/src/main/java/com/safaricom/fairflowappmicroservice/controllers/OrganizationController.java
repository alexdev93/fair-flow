package com.safaricom.fairflowappmicroservice.controllers;

import com.safaricom.fairflowappmicroservice.dtos.Organization.OrganizationRequestDto;
import com.safaricom.fairflowappmicroservice.dtos.Organization.OrganizationResponseDto;
import com.safaricom.fairflowappmicroservice.dtos.Representative.RepresentativeResponseDto;
import com.safaricom.fairflowappmicroservice.services.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/api/v1/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    public ResponseEntity<List<OrganizationResponseDto>> getOrganizations() {
        return new ResponseEntity<>(organizationService.
                listAllOrganizations().
                stream().
                map((organization) -> new OrganizationResponseDto(organization)).
                collect(Collectors.toList())

                , HttpStatus.OK);
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<OrganizationResponseDto> getOrganization(@PathVariable Long organizationId) {
        return new ResponseEntity<>(
                new OrganizationResponseDto(organizationService.getOrganization(organizationId)),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrganizationResponseDto> saveOrganization(@Valid @RequestBody OrganizationRequestDto organizationDto) {
        return new ResponseEntity<>(
                new OrganizationResponseDto(organizationService.saveOrganization(organizationDto)),
                HttpStatus.CREATED);
    }

    @PutMapping("/{organizationId}")
    public ResponseEntity<OrganizationResponseDto> updateOrganization(@PathVariable Long organizationId,
                                                                      @Valid @RequestBody OrganizationRequestDto organizationDto) {
        return new ResponseEntity<>(
                new OrganizationResponseDto(organizationService.updateOrganization(organizationId, organizationDto)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{organizationId}")
    public ResponseEntity<HttpStatus> deleteOrganization(@PathVariable Long organizationId) {
        organizationService.deleteOrganization(organizationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("representatives/{organizationId}")
    public ResponseEntity<List<RepresentativeResponseDto>> getRepresentatives(@PathVariable Long organizationId) {
        return new ResponseEntity<>(
                organizationService.getOrganization(organizationId).getRepresentatives().
                        stream().
                        map((r) -> new RepresentativeResponseDto(r)).
                        collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
