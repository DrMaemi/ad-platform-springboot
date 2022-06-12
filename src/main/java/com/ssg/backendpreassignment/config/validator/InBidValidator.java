package com.ssg.backendpreassignment.config.validator;

import com.ssg.backendpreassignment.repository.AdBidRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 광고과금 데이터 생성 API 호출 시 등록되지 않은 광고입찰 ID인지 검사
 * 입력받은 광고입찰 ID가 DB에 생성되어 있는지 여부 검사
 */
@RequiredArgsConstructor
public class InBidValidator implements ConstraintValidator<InBid, Long> {
    private final AdBidRepository adBidRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return adBidRepository.existsById(value);
    }
}
