package com.project.dasarang.global.security.api.presentation;

import com.project.dasarang.global.security.api.presentation.dto.request.GenerateKeyRequest;
import com.project.dasarang.global.security.api.presentation.dto.response.KeyListResponse;
import com.project.dasarang.global.security.api.presentation.dto.response.KeyResponse;
import com.project.dasarang.global.security.api.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @PostMapping("/generate")
    public KeyResponse generateKey(
            @RequestBody GenerateKeyRequest request
    ) {
        return apiService.generateApiKey(request, 2592000L);
    }

    @GetMapping("/key/list")
    public KeyListResponse getApiKeyList(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return apiService.getApiKeyList(page, size);
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApiKey(
            @RequestParam("name") String name,
            @RequestParam("number") String number
    ) {
        apiService.deleteApiKey(name, number);
    }

}
