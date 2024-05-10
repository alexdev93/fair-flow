package com.safaricom.fairflowappmicroservice.services;

import com.safaricom.fairflowappmicroservice.dtos.Organization.OrganizationRequestDto;
import com.safaricom.fairflowappmicroservice.exceptions.NotFoundException;
import com.safaricom.fairflowappmicroservice.models.Organization;
import com.safaricom.fairflowappmicroservice.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization getOrganization(Long organizationId) {
        return organizationRepository.findById(organizationId).
                orElseThrow(() -> new NotFoundException("Organization not found"));
    }

    public Organization saveOrganization(OrganizationRequestDto organizationDto) {
        return organizationRepository.save(new Organization(organizationDto));
    }

    public Organization updateOrganization(Long organizationId, OrganizationRequestDto organizationDto) {
        Organization organization = new Organization(organizationDto);
        organization.setId(organizationId);
        return organizationRepository.save(organization);
    }

    public List<Organization> listAllOrganizations() {
        return organizationRepository.findAll();
    }

    public void deleteOrganization(Long organizationId) {
        organizationRepository.deleteById(organizationId);
    }

}
