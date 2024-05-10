package com.safaricom.fairflowappmicroservice.repositories;

import com.safaricom.fairflowappmicroservice.models.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentativeRepository extends JpaRepository<Representative, Long> {
}
