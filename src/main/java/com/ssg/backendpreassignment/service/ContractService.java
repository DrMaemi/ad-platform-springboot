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

/**
 * 계약 도메인의 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final CompanyRepository companyRepository;

    /**
     * 생성된 계약 정보 리스트 조회 요청을 처리하기 위한 서비스 메서드
     * @return ArrayList
     */
    @Transactional(readOnly=true)
    public List<ContractDto> getContracts() {
        List<ContractEntity> contractEntities = contractRepository.findAllJpqlFetch();
        return contractEntities.stream().map(contractEntity -> contractEntity.toDto()).collect(Collectors.toList());
    }

    /**
     * 광고입찰 생성 시 업체의 계약 유무를 검사하는 유효성 검사에 필요한 서비스 메서드
     * 입력받은 업체 ID를 가지는 계약 정보를 DB에서 조회한 결과 반환
     * @param companyId
     * @return ContractDto
     */
    @Transactional(readOnly=true)
    public ContractDto findByCompanyId(Long companyId) {
        Optional<ContractEntity> contractEntityWrapper = contractRepository.findByCompanyIdJpqlFetch(companyId);

        if (contractEntityWrapper.isPresent()) {
            return contractEntityWrapper.get().toDto();
        }

        return null;
    }

    /**
     * 계약 생성 요청을 처리하기 위한 서비스 메서드
     * 요청 데이터를 받아 엔티티를 생성하여 영속성 컨텍스트에서 관리되도록 처리
     * @param contractReqDto
     * @return ContractDto
     */
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
