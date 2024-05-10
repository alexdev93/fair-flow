package com.safaricom.fairflowappmicroservice.repositories;

import com.safaricom.fairflowappmicroservice.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
