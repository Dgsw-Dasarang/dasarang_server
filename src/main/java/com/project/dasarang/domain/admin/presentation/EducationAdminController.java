package com.project.dasarang.domain.admin.presentation;

import com.project.dasarang.domain.admin.presentation.dto.request.UpdateEducationInfoRequest;
import com.project.dasarang.domain.admin.service.AdminUpdateEducationInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/education")
@RequiredArgsConstructor
@Tag(name = "학원 데이터 관리 서버")
public class EducationAdminController {

    private final AdminUpdateEducationInfoService adminUpdateEducationInfoService;

    @Operation(summary = "학원 API 데이터 변경")
    @PatchMapping("/{number}")
    public void updateEducation(
            @PathVariable("number") String number,
            @RequestBody UpdateEducationInfoRequest request
    ) {
        adminUpdateEducationInfoService.execute(number, request);
    }

}
