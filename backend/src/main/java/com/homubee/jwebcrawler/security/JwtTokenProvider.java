package com.homubee.jwebcrawler.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
    @Value("${jwt.access-token-key}")
    private String accessTokenSecretKey;
    @Value("${jwt.refresh-token-key}")
    private String refreshTokenSecretKey;

    private Long accessTokenValidTime = 3 * 60 * 1000L; // 3 min (for test)
    private Long refreshTokenValidTime = 5 * 60 * 1000L; // 5 min (for test)

    @Autowired
    private CustomUserDetailService CustomUserDetailService;

    @PostConstruct
    protected void init() {
        this.accessTokenSecretKey = Base64.getEncoder().encodeToString(accessTokenSecretKey.getBytes());
        this.refreshTokenSecretKey = Base64.getEncoder().encodeToString(refreshTokenSecretKey.getBytes());
    }

    public String createToken(String userPk, List<String> roles, Boolean isAccess) {
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
        Date now = new Date();
        if (isAccess) {
            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + accessTokenValidTime))
                    .signWith(SignatureAlgorithm.HS256, accessTokenSecretKey)
                    .compact();
        } else {
            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + refreshTokenValidTime))
                    .signWith(SignatureAlgorithm.HS256, refreshTokenSecretKey)
                    .compact();
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = CustomUserDetailService.loadUserByUsername(this.getAccessUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String getAccessUserPk(String token) {
        return Jwts.parser().setSigningKey(accessTokenSecretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String getRefreshUserPk(String token) {
        return Jwts.parser().setSigningKey(refreshTokenSecretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            token = token.replace("Bearer ", "");
        }
        return token;
    }

    public boolean validateToken(String token, Boolean isAccess) {
        try {
            if (isAccess) {
                Jwts.parser().setSigningKey(accessTokenSecretKey).parseClaimsJws(token);
            } else {
                Jwts.parser().setSigningKey(refreshTokenSecretKey).parseClaimsJws(token);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
