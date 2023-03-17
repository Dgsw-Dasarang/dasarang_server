package com.project.dasarang.global.infra.education.service;

import com.project.dasarang.global.config.AppProperties;
import com.project.dasarang.global.config.WebClientConfiguration;
import com.project.dasarang.global.infra.education.domain.repository.EducationRepository;
import com.project.dasarang.global.infra.education.domain.repository.TuitionRepository;
import com.project.dasarang.global.infra.education.dto.ApiDto;
import com.project.dasarang.global.infra.education.dto.EducationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final TuitionRepository tuitionRepository;
    private final WebClientConfiguration webClient;
    private final AppProperties appProperties;

//    @Transactional
    public Mono<ApiDto> excute(String code) {
        Mono<ApiDto> response = webClient.apiClient()
                .get()
                .uri("?KEY=" + appProperties.getApiKey() + "&Type=" + appProperties.getType() + "&ATPT_OFCDC_SC_CODE=" + code)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ApiDto.class);

        log.info(String.valueOf(response));

        return response;
    }

}
