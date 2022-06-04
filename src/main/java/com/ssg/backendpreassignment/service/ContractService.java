package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.dto.ContractDto;
import com.ssg.backendpreassignment.entity.CompanyEntity;
import com.ssg.backendpreassignment.entity.ContractEntity;
import com.ssg.backendpreassignment.repository.CompanyRepository;
import com.ssg.backendpreassignment.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final CompanyRepository companyRepository;

    @Transactional(readOnly=true)
    public List<ContractDto> getContracts() {
        List<ContractEntity> contractEntities = contractRepository.findAll();
        return contractEntities.stream().map(contractEntity -> contractEntity.toDto()).collect(Collectors.toList());
    }

    @Transactional
    public void doContract(ContractDto contractDto) {
        Long companyId = contractDto.getCompanyDto().getId();
        Optional<CompanyEntity> companyEntityWrapper = companyRepository.findById(companyId);
        CompanyEntity companyEntity = companyEntityWrapper.get();
        ContractEntity contractEntity = contractDto.toEntity();
        contractEntity.setCompanyEntity(companyEntity);
        contractRepository.save(contractEntity);
    }
}
