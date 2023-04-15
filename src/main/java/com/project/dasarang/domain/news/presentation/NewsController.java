package com.project.dasarang.domain.news.presentation;

import com.project.dasarang.domain.news.domain.enums.SearchCategory;
import com.project.dasarang.domain.news.presentation.dto.request.CreateNewsRequest;
import com.project.dasarang.domain.news.presentation.dto.request.UpdateNewsRequest;
import com.project.dasarang.domain.news.presentation.dto.response.NewsListResponse;
import com.project.dasarang.domain.news.presentation.dto.response.NewsResponse;
import com.project.dasarang.domain.news.service.NewsService;
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

    private final NewsService newsService;

    @Operation(summary = "소식 작성하기")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createNews(
            @RequestBody CreateNewsRequest request
    ) {
        newsService.createNews(request);
    }

    @Operation(summary = "소식 수정하기")
    @PatchMapping("/{id}")
    public void updateNews(
            @PathVariable("id") Long id,
            @RequestBody UpdateNewsRequest request
    ) {
        newsService.updateNews(id, request);
    }

    @Operation(summary = "소식 삭제하기")
    @DeleteMapping("/{id}")
    public void deleteNews(
            @PathVariable("id") Long id
    ) {
        newsService.deleteNews(id);
    }

    @Operation(summary = "전체 소식데이터 조회하기 (page랑 size랑 데이터 필요)")
    @GetMapping("/list/column")
    public NewsListResponse getNewsAllByCategory(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("category") SearchCategory category,
            @RequestParam("content") String content
    ) {
        return newsService.getNewsAllByCategory(page, size, category, content);
    }

    @Operation(summary = "소식 전체 조회하기")
    @GetMapping("/list")
    public NewsListResponse getNewsAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return newsService.getNewsAll(page, size);
    }

    @Operation(summary = "소식 상세 조회하기")
    @GetMapping("/{id}")
    public NewsResponse getNewsById(
            @PathVariable("id") Long id
    ) {
        return newsService.getNewsById(id);
    }

}
