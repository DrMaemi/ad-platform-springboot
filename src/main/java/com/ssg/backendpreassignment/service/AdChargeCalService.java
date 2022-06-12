package com.ssg.backendpreassignment.service;

import com.ssg.backendpreassignment.dto.AdChargeCalDto;
import com.ssg.backendpreassignment.repository.AdChargeCalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdChargeCalService {
    private final AdChargeCalRepository adChargeCalRepository;

    @Transactional
    public List<AdChargeCalDto> getAdChargeCals() {
        return adChargeCalRepository.findAll().stream().map(ent -> ent.toDto()).collect(Collectors.toList());
    }
}
