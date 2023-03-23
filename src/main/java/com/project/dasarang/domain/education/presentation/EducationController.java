package com.project.dasarang.domain.education.presentation;

import com.project.dasarang.domain.education.presentation.dto.response.EducationListResponse;
import com.project.dasarang.domain.education.presentation.dto.response.EducationResponse;
import com.project.dasarang.domain.education.service.EducationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("education")
@RequiredArgsConstructor
@Tag(name = "교육 서버")
public class EducationController {

    private final EducationService educationService;

    @Operation(summary = "학원명으로 학원데이터 조회하기")
    @GetMapping("/{academy-name}")
    public EducationResponse getEducationByAcademyName(
            @PathVariable("academy-name") String academyName
    ) {
        return educationService.getEducationByAcaName(academyName);
    }

    @Operation(summary = "전체 학원데이터 조회하기 (page랑 size 필요)")
    @GetMapping("/list")
    public EducationListResponse getEducationAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return educationService.getEducationAll(page, size);
    }

    @Operation(summary = "학원 상태로 전체 학원데이터 조회하기 (page랑 size, 학원 상태 필요)")
    @GetMapping("/list/{status}")
    public EducationListResponse getEducationAllByStatus(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @PathVariable("status") String status
    ) {
        return educationService.getEducationAllByStatus(status, page, size);
    }

}
