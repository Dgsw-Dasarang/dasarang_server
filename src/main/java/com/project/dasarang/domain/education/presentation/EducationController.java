package com.project.dasarang.domain.education.presentation;

import com.project.dasarang.domain.education.presentation.dto.response.EducationListResponse;
import com.project.dasarang.domain.education.presentation.dto.response.EducationResponse;
import com.project.dasarang.domain.education.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @GetMapping("/{academy-name}")
    public EducationResponse getEducationByAcademyName(
            @PathVariable("academy-name") String academyName
    ) {
        return educationService.getEducationByAcaName(academyName);
    }

    @GetMapping("/list")
    public EducationListResponse getEducationAllByAcademyName(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return educationService.getEducationAll(page, size);
    }

    @GetMapping("/list/{status}")
    public EducationListResponse getEducationAllByAcademyName(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @PathVariable("status") String status
    ) {
        return educationService.getEducationAllByStatus(status, page, size);
    }

}
