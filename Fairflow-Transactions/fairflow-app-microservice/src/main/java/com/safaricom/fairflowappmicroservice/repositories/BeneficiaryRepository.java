package com.safaricom.fairflowappmicroservice.repositories;

import com.safaricom.fairflowappmicroservice.models.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
}
