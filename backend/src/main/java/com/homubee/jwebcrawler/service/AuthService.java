package com.homubee.jwebcrawler.service;

import com.homubee.jwebcrawler.domain.Member;
import com.homubee.jwebcrawler.dto.request.MemberLoginRequestDTO;
import com.homubee.jwebcrawler.repository.MemberRepository;
import com.homubee.jwebcrawler.security.JwtTokenProvider;
import com.homubee.jwebcrawler.security.TokenInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.util.List;

@Service
@Transactional
public class AuthService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public TokenInfo login(MemberLoginRequestDTO requestDTO) {
        // check email
        List<Member> findMembers = memberRepository.findByEmail(requestDTO.getEmail());
        if (findMembers.isEmpty()) {
            throw new BadCredentialsException("Member not exists.");
        }

        // check password
        Member member = findMembers.get(0);
        if (!passwordEncoder.matches(requestDTO.getPassword(), member.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        // set tokens
        TokenInfo tokenInfo = new TokenInfo();
        String accessToken = jwtTokenProvider.createToken(member.getEmail(), member.getRoleStringList(), true);
        String refreshToken = jwtTokenProvider.createToken(member.getEmail(), member.getRoleStringList(), false);
        tokenInfo.setMemberId(member.getId());
        tokenInfo.setAccessToken(accessToken);
        tokenInfo.setRefreshToken(refreshToken);
        member.setRefreshToken(refreshToken);
        memberRepository.save(member);

        return tokenInfo;
    }

    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // check email
        List<Member> findMembers = memberRepository.findByEmail(authentication.getName());
        if (findMembers.isEmpty()) {
            throw new IllegalStateException("Member not exists.");
        }

        // clear refresh token
        Member member = findMembers.get(0);
        member.setRefreshToken("");
        memberRepository.save(member);
    }

    public String refresh(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            // find refresh token cookie
            if (cookie.getName().equals("jweb_refresh_token")) {
                String token = cookie.getValue();
                if (token != null && jwtTokenProvider.validateToken(token, false)) {
                    String email = jwtTokenProvider.getRefreshUserPk(token);

                    List<Member> members = memberRepository.findByEmail(email);

                    if (!members.isEmpty()) {
                        Member member = members.get(0);
                        if (member.getRefreshToken().equals(token)) {
                            return jwtTokenProvider.createToken(email, member.getRoleStringList(), true);
                        }
                    }
                }
                break;
            }
        }
        return "";
    }
}
