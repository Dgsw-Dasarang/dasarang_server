package com.project.dasarang.global.security;

import com.project.dasarang.domain.user.domain.enums.UserType;
import com.project.dasarang.global.security.jwt.JwtProvider;
import com.project.dasarang.global.security.jwt.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtProvider jwtProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web ->
                web.ignoring()
                        .mvcMatchers("/swagger-ui/**", "/configuration/**", "/swagger-resources/**", "/v3/api-docs/**", "/webjars/**", "/webjars/springfox-swagger-ui/*.{js,css}", "/bus/v3/api-docs/**");
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

                // 인증 서버
                .antMatchers(HttpMethod.POST, "/auth/**").permitAll() // 로그인, 회원가입
                .antMatchers(HttpMethod.GET, "/auth/refresh").authenticated() // 토큰 재발급

                // 유저 서버
                .antMatchers(HttpMethod.GET, "/user/**").authenticated() // 유저 정보
                .antMatchers(HttpMethod.PATCH, "/user/**").hasRole(UserType.ROLE_USER.getRole())
                .antMatchers(HttpMethod.PATCH, "/owner/**").hasRole(UserType.ROLE_OWNER.getRole())

                // 교육 서버
                .antMatchers(HttpMethod.GET, "/education/num/**").hasRole(UserType.ROLE_OWNER.getRole())
                .antMatchers(HttpMethod.GET, "/education/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/education/**").hasRole(UserType.ROLE_ADMIN.getRole())

                // 글 서버
                .antMatchers(HttpMethod.POST, "/post/**").hasRole(UserType.ROLE_OWNER.getRole())
                .antMatchers(HttpMethod.PATCH, "/post/**").hasRole(UserType.ROLE_OWNER.getRole())
                .antMatchers(HttpMethod.DELETE, "/post/**").hasRole(UserType.ROLE_OWNER.getRole())
                .antMatchers(HttpMethod.GET, "/post/**").permitAll()

                // 이미지 서버
                .antMatchers(HttpMethod.POST, "/upload/**").authenticated()
                .antMatchers(HttpMethod.GET, "/upload/**").permitAll()

                // 관리자 서버
                .antMatchers("/admin/**").hasRole(UserType.ROLE_ADMIN.getRole())

                // 소식 서버
                .antMatchers(HttpMethod.POST, "/news/**").hasRole(UserType.ROLE_ADMIN.getRole())
                .antMatchers(HttpMethod.GET, "/news/**").permitAll()

                // 정적 페이지
                .antMatchers("/*.html").permitAll()

                .anyRequest().denyAll()
                .and()
                .addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
