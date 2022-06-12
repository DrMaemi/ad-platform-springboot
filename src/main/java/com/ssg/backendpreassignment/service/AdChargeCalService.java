package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.dto.AdChargeCalDto;
import com.ssg.backendpreassignment.repository.AdChargeCalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 광고과금정산 도메인의 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class AdChargeCalService {
    private final AdChargeCalRepository adChargeCalRepository;

    /**
     * 광고과금정산 데이터 리스트 조회 요청에 대한 서비스 메서드
     * @return ArrayList
     */
    @Transactional(readOnly=true)
    public List<AdChargeCalDto> getAdChargeCals() {
        return adChargeCalRepository.findAll().stream().map(ent -> ent.toDto()).collect(Collectors.toList());
    }
}
