package com.project.dasarang.global.security.jwt;

import com.project.dasarang.domain.auth.domain.RefreshToken;
import com.project.dasarang.domain.auth.domain.enums.UserType;
import com.project.dasarang.domain.auth.domain.repository.RefreshTokenRepository;
import com.project.dasarang.global.security.auth.AuthDetailsService;
import com.project.dasarang.global.security.auth.UserToken;
import com.project.dasarang.global.security.jwt.exception.ExpiredTokenException;
import com.project.dasarang.global.security.jwt.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthDetailsService authDetailsService;

    public String generateAccessToken(String id, UserType type) {
        return generateToken(id, type, jwtProperties.getAccessExp());
    }

    public String generateRefreshToken(String id, UserType type) {
        String token = generateToken(id, type, jwtProperties.getRefreshExp());

        refreshTokenRepository.save(new RefreshToken(token, id));
        return token;
    }

    @Transactional
    public Authentication authentication(String token) {
        return new UserToken(authDetailsService.loadUserByUsername(getTokenSubject(token)));
    }

    public String resolveToken(HttpServletRequest request) {
        return parseToken(request.getHeader(jwtProperties.getHeader()));
    }

    public String getTokenSubject(String token) {
        return getTokenBody(token).getSubject();
    }

    public Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private String parseToken(String bearerToken) {
        if(bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())) {
            return bearerToken.replace(jwtProperties.getPrefix(), "");
        }
        return null;
    }

    private String generateToken(String id, UserType type, Long exp) {
        return Jwts.builder()
                .setSubject(id)
                .claim("type", type)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

}
