package com.project.dasarang.global.security.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;


public class UserToken extends UsernamePasswordAuthenticationToken {

    public UserToken(UserDetails userDetails) {
        super(userDetails, null, userDetails.getAuthorities());
    }
}
