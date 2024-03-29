package com.project.dasarang.domain.news.presentation;

import com.project.dasarang.domain.news.domain.enums.SearchCategory;
import com.project.dasarang.domain.news.presentation.dto.request.CreateNewsRequest;
import com.project.dasarang.domain.news.presentation.dto.request.UpdateNewsRequest;
import com.project.dasarang.domain.news.presentation.dto.response.NewsListResponse;
import com.project.dasarang.domain.news.presentation.dto.response.NewsResponse;
import com.project.dasarang.domain.news.service.CreateNewsService;
import com.project.dasarang.domain.news.service.DeleteNewsService;
import com.project.dasarang.domain.news.service.FindAllNewsByCategoryService;
import com.project.dasarang.domain.news.service.FindAllNewsService;
import com.project.dasarang.domain.news.service.FindNewsByIdService;
import com.project.dasarang.domain.news.service.UpdateNewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@Tag(name = "소식 서버")
public class NewsController {

    private final CreateNewsService createNewsService;
    private final UpdateNewsService updateNewsService;
    private final DeleteNewsService deleteNewsService;
    private final FindAllNewsByCategoryService findAllNewsByCategoryService;
    private final FindAllNewsService findAllNewsService;
    private final FindNewsByIdService findNewsByIdService;

    @Operation(summary = "소식 작성하기")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createNews(
            @RequestBody CreateNewsRequest request
    ) {
        createNewsService.execute(request);
    }

    @Operation(summary = "소식 수정하기")
    @PatchMapping("/{id}")
    public void updateNews(
            @PathVariable("id") Long id,
            @RequestBody UpdateNewsRequest request
    ) {
        updateNewsService.execute(id, request);
    }

    @Operation(summary = "소식 삭제하기")
    @DeleteMapping("/{id}")
    public void deleteNews(
            @PathVariable("id") Long id
    ) {
        deleteNewsService.execute(id);
    }

    @Operation(summary = "전체 소식데이터 조회하기 (page랑 size랑 데이터 필요)")
    @GetMapping("/list/column")
    public NewsListResponse getNewsAllByCategory(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("category") SearchCategory category,
            @RequestParam("content") String content
    ) {
        return findAllNewsByCategoryService.execute(page, size, category, content);
    }

    @Operation(summary = "소식 전체 조회하기")
    @GetMapping("/list")
    public NewsListResponse getNewsAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return findAllNewsService.execute(page, size);
    }

    @Operation(summary = "소식 상세 조회하기")
    @GetMapping("/{id}")
    public NewsResponse getNewsById(
            @PathVariable("id") Long id
    ) {
        return findNewsByIdService.execute(id);
    }

}
