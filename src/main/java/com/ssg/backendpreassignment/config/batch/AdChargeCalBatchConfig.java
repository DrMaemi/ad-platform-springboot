package com.ssg.backendpreassignment.config.batch;

import com.ssg.backendpreassignment.entity.AdChargeCalEntity;
import com.ssg.backendpreassignment.repository.AdChargeCalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class AdChargeCalBatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final AdChargeCalRepository adChargeCalRepository;


    @Bean
    public Job job() {
        Job job = jobBuilderFactory.get("job")
                .start(step())
                .build();

        return job;
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .tasklet(((contribution, chunkContext) -> {
                    List<AdChargeCalEntity> adChargeCalEntities = adChargeCalRepository.dailyCal();
                    for (AdChargeCalEntity adChargeCalEntity: adChargeCalEntities) {
                        System.out.println("adChargeCalEntity : "+adChargeCalEntity);
                    }
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }
}
