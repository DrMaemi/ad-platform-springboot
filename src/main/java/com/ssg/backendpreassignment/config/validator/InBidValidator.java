package com.ssg.backendpreassignment.config.validator;

import com.ssg.backendpreassignment.repository.AdBidRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class InBidValidator implements ConstraintValidator<InBid, Long> {
    private final AdBidRepository adBidRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return adBidRepository.existsById(value);
    }
}
