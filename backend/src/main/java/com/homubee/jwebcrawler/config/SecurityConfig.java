package com.homubee.jwebcrawler.config;

import com.homubee.jwebcrawler.security.JwtAccessDeniedHandler;
import com.homubee.jwebcrawler.security.JwtAuthenticationEntryPoint;
import com.homubee.jwebcrawler.security.JwtAuthenticationFilter;
import com.homubee.jwebcrawler.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .authorizeRequests()
                // option 메소드는 전부 권한 없이 공개
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                // swagger, 회원가입, 인증 관련 API는 권한 없이 공개
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api/v1/auth/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/members")
                .permitAll()
                // 회원 전체 조회, 크롤링 이력 전체 조회하는 API는 ADMIN 권한에게만 공개
                .antMatchers(HttpMethod.GET, "/api/v1/members", "/api/v1/crawl")
                .hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
