package com.project.dasarang.domain.auth.presentation;

import com.project.dasarang.domain.auth.presentation.dto.request.OwnerSignUpRequest;
import com.project.dasarang.domain.auth.presentation.dto.request.SignInRequest;
import com.project.dasarang.domain.auth.presentation.dto.request.UserSignUpRequest;
import com.project.dasarang.domain.auth.presentation.dto.response.UserTokenResponse;
import com.project.dasarang.domain.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/sign-up")
    public void userSignUp(
            @RequestBody UserSignUpRequest request
    ) {
        userService.userSignUp(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/owner/sign-up")
    public void ownerSignUp(
            @RequestBody OwnerSignUpRequest request
    ) {
        userService.ownerSignUp(request);
    }

    @PostMapping("/sign-in")
    public UserTokenResponse userSignIn(
            @RequestBody SignInRequest request
    ) {
        return userService.userSignIn(request);
    }

}
