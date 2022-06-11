package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.dto.ContractDto;
import com.ssg.backendpreassignment.dto.ContractReqDto;
import com.ssg.backendpreassignment.entity.CompanyEntity;
import com.ssg.backendpreassignment.entity.ContractEntity;
import com.ssg.backendpreassignment.repository.CompanyRepository;
import com.ssg.backendpreassignment.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        List<ContractEntity> contractEntities = contractRepository.findAllJpqlFetch();
        return contractEntities.stream().map(contractEntity -> contractEntity.toDto()).collect(Collectors.toList());
    }

    @Transactional(readOnly=true)
    public ContractDto findByCompanyId(Long companyId) {
        Optional<ContractEntity> contractEntityWrapper = contractRepository.findByCompanyIdJpqlFetch(companyId);

        if (contractEntityWrapper.isPresent()) {
            return contractEntityWrapper.get().toDto();
        }

        return null;
    }

    @Transactional
    public ContractDto createContract(ContractReqDto contractReqDto) {
        Long companyId = contractReqDto.getCompanyId();
        CompanyEntity companyEntity = companyRepository.findById(companyId).get();
        ContractEntity contractEntity = ContractEntity.builder()
                .companyEntity(companyEntity)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(1))
                .build();
        ContractEntity resEnt = contractRepository.save(contractEntity);
        return resEnt.toDto();
    }
}
