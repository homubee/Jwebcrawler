package com.homubee.jwebcrawler.controller;

import com.homubee.jwebcrawler.dto.request.MemberLoginRequestDTO;
import com.homubee.jwebcrawler.dto.response.MemberLoginResponseDTO;
import com.homubee.jwebcrawler.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "[App] Authentication", description = "인증 API")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public MemberLoginResponseDTO login(@RequestBody MemberLoginRequestDTO requestDTO) {
        return authService.login(requestDTO);
    }
}
