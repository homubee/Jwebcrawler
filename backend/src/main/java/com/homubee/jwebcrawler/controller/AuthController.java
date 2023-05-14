package com.homubee.jwebcrawler.controller;

import com.homubee.jwebcrawler.dto.request.MemberLoginRequestDTO;
import com.homubee.jwebcrawler.dto.response.MemberLoginResponseDTO;
import com.homubee.jwebcrawler.security.TokenInfo;
import com.homubee.jwebcrawler.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "[App] Authentication", description = "인증 API")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public MemberLoginResponseDTO login(@RequestBody MemberLoginRequestDTO requestDTO, HttpServletResponse response) {
        TokenInfo tokenInfo = authService.login(requestDTO);

        ResponseCookie cookie = ResponseCookie.from("jweb_refresh_token", tokenInfo.getRefreshToken())
                .maxAge(7 * 24 * 60 * 60)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        response.setHeader("Set-Cookie", cookie.toString());

        MemberLoginResponseDTO responseDTO = new MemberLoginResponseDTO();
        responseDTO.setAccessToken(tokenInfo.getAccessToken());
        return responseDTO;
    }

    @PostMapping("/logout")
    public void logout() {
        authService.logout();
    }

    @PostMapping("/refresh")
    public ResponseEntity<MemberLoginResponseDTO> refresh(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        String accessToken = authService.refresh(cookies);

        if (accessToken.isEmpty()) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        MemberLoginResponseDTO responseDTO = new MemberLoginResponseDTO();
        responseDTO.setAccessToken(accessToken);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }
}
