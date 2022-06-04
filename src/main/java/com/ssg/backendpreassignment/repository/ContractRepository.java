package com.ssg.backendpreassignment.repository;

import com.ssg.backendpreassignment.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
}
