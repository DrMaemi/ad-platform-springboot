package com.ssg.backendpreassignment.config.batch;

import com.ssg.backendpreassignment.dto.AdChargeCalDto;
import com.ssg.backendpreassignment.entity.AdChargeCalEntity;
import com.ssg.backendpreassignment.repository.AdChargeCalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@RequiredArgsConstructor
public class AdChargeCalBatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ResourcelessTransactionManager resourcelessTransactionManager;
    private final AdChargeCalRepository adChargeCalRepository;

    @Bean
    @StepScope
    public ListItemReader<AdChargeCalEntity> listItemReader() {
        List<AdChargeCalEntity> adChargeCalEntities = adChargeCalRepository.dailyCal();
        return new ListItemReader<>(adChargeCalEntities);
    }

    @Bean
    @StepScope
    public ItemProcessor<AdChargeCalEntity, AdChargeCalDto> itemProcessor() {
        return AdChargeCalEntity::toDto;
    }

    @Bean
    @StepScope
    public ItemWriter<AdChargeCalEntity> itemWriter() {
        return ((List<? extends AdChargeCalEntity> adChargeCalEntities) ->
                adChargeCalRepository.saveAll(adChargeCalEntities));
    }

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
                .transactionManager(resourcelessTransactionManager)
                .<AdChargeCalEntity, AdChargeCalEntity> chunk(100)
                .reader(listItemReader())
                .writer(itemWriter())
                .build();
    }
}
