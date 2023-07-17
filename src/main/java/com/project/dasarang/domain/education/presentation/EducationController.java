package com.project.dasarang.domain.education.presentation;

import com.project.dasarang.domain.education.service.FindAllEducationByCategoryService;
import com.project.dasarang.domain.education.service.FindAllEducationByStatusService;
import com.project.dasarang.domain.education.service.FindAllEducationService;
import com.project.dasarang.domain.education.service.FindEducationByAcaAsnumService;
import com.project.dasarang.domain.education.service.FindEducationByAcaNameService;
import com.project.dasarang.global.infra.education.domain.enums.EducationCategory;
import com.project.dasarang.domain.education.presentation.dto.response.EducationListResponse;
import com.project.dasarang.domain.education.presentation.dto.response.EducationResponse;
import com.project.dasarang.global.infra.education.service.EducationApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("education")
@RequiredArgsConstructor
@Tag(name = "교육 서버")
public class EducationController {

    private final FindAllEducationByCategoryService findAllEducationByCategoryService;
    private final FindAllEducationByStatusService findAllEducationByStatusService;
    private final FindAllEducationService findAllEducationService;
    private final FindEducationByAcaAsnumService findEducationByAcaAsnumService;
    private final FindEducationByAcaNameService findEducationByAcaNameService;
    private final EducationApiService educationApiService;

    @Operation(summary = "학원지정번호로 학원데이터 조회하기")
    @GetMapping("/num/{academy-number}")
    public EducationResponse getEducationByAcaAsnum(
            @PathVariable("academy-number") String acaAsnum
    ) {
        return findEducationByAcaAsnumService.execute(acaAsnum);
    }

    @Operation(summary = "학원명으로 학원데이터 조회하기")
    @GetMapping("/{academy-name}")
    public EducationResponse getEducationByAcademyName(
            @PathVariable("academy-name") String academyName
    ) {
        return findEducationByAcaNameService.execute(academyName);
    }

    @Operation(summary = "전체 학원데이터 조회하기 (page랑 size 필요)")
    @GetMapping("/list")
    public EducationListResponse getEducationAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return findAllEducationService.execute(page, size);
    }

    @Operation(summary = "카테고리로 학원데이터 검색하기 (page랑 size랑 데이터 필요)")
    @GetMapping("/list/category")
    public EducationListResponse getEducationByCategory(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("category") EducationCategory category,
            @RequestParam("content") String content
    ) {
        return findAllEducationByCategoryService.execute(category, content, page, size);
    }

    @Operation(summary = "학원 상태로 전체 학원데이터 조회하기 (page랑 size, 학원 상태 필요)")
    @GetMapping("/list/{status}")
    public EducationListResponse getEducationAllByStatus(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @PathVariable("status") String status
    ) {
        return findAllEducationByStatusService.execute(status, page, size);
    }

    @Operation(summary = "API 데이터 업데이트")
    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/api/update")
    public void apiUpdate(
            @RequestParam("code") String code
    ) throws JSONException {
        educationApiService.execute(code);
    }

}
