package com.homubee.jwebcrawler.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenInfo {
    private String accessToken;
    private String refreshToken;
}
