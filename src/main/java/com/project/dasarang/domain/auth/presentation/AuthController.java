package com.project.dasarang.domain.auth.presentation;

import com.project.dasarang.domain.auth.presentation.dto.request.OwnerSignUpRequest;
import com.project.dasarang.domain.auth.presentation.dto.request.SignInRequest;
import com.project.dasarang.domain.auth.presentation.dto.request.UserSignUpRequest;
import com.project.dasarang.domain.auth.presentation.dto.response.TokenResponse;
import com.project.dasarang.domain.auth.service.OwnerSignUpService;
import com.project.dasarang.domain.auth.service.RefreshAccessTokenService;
import com.project.dasarang.domain.auth.service.SignInService;
import com.project.dasarang.domain.auth.service.UserSignUpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "인증 서버")
public class AuthController {

    private final UserSignUpService userSignUpService;
    private final OwnerSignUpService ownerSignUpService;
    private final SignInService signInService;
    private final RefreshAccessTokenService refreshAccessTokenService;

    @Operation(summary = "유저 회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/sign-up")
    public void userSignUp(
            @RequestBody UserSignUpRequest request
    ) {
        userSignUpService.execute(request);
    }

    @Operation(summary = "업체 회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/owner/sign-up")
    public void ownerSignUp(
            @RequestBody OwnerSignUpRequest request
    ) {
        ownerSignUpService.execute(request);
    }

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public TokenResponse signIn(
            @RequestBody SignInRequest request
    ) {
        return signInService.execute(request);
    }

    @Operation(summary = "토큰 재발급")
    @GetMapping("/refresh")
    public String refreshAccessToken() {
        return refreshAccessTokenService.execute();
    }

}
