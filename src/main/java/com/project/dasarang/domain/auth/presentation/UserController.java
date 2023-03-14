package com.project.dasarang.domain.auth.presentation;

import com.project.dasarang.domain.auth.presentation.dto.request.SignInRequest;
import com.project.dasarang.domain.auth.presentation.dto.request.SignUpRequest;
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
    @PostMapping("/sign-up")
    public void userSignUp(
            @RequestBody SignUpRequest request
    ) {
        userService.userSignUp(request);
    }

    @PostMapping("/sign-in")
    public UserTokenResponse userSignIn(
            @RequestBody SignInRequest request
    ) {
        return userService.userSignIn(request);
    }

}
