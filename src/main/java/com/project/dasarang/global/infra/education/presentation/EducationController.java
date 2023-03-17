package com.project.dasarang.global.infra.education.presentation;

import com.project.dasarang.global.infra.education.dto.ApiDto;
import com.project.dasarang.global.infra.education.dto.EducationDto;
import com.project.dasarang.global.infra.education.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("education")
public class EducationController {

    private final EducationService educationService;

    @GetMapping("/")
    public Mono<ApiDto> get() {
        return educationService.excute("D10");
    }

}
