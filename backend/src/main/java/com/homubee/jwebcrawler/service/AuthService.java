package com.homubee.jwebcrawler.service;

import com.homubee.jwebcrawler.domain.Member;
import com.homubee.jwebcrawler.dto.request.MemberLoginRequestDTO;
import com.homubee.jwebcrawler.dto.response.MemberLoginResponseDTO;
import com.homubee.jwebcrawler.repository.MemberRepository;
import com.homubee.jwebcrawler.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public MemberLoginResponseDTO login(MemberLoginRequestDTO requestDTO) {
        MemberLoginResponseDTO responseDTO = new MemberLoginResponseDTO();

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
        responseDTO.setAccessToken(jwtTokenProvider.createToken(member.getEmail(),
                member.getRoleList().stream().map(role -> role.getRoleType().toString()).collect(Collectors.toList()),
                true));
        responseDTO.setRefreshToken(jwtTokenProvider.createToken(member.getEmail(),
                member.getRoleList().stream().map(role -> role.getRoleType().toString()).collect(Collectors.toList()),
                false));

        return responseDTO;
    }
}
