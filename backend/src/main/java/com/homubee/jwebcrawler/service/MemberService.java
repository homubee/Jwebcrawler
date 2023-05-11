package com.homubee.jwebcrawler.service;

import com.homubee.jwebcrawler.domain.Member;
import com.homubee.jwebcrawler.domain.Role;
import com.homubee.jwebcrawler.domain.RoleType;
import com.homubee.jwebcrawler.dto.request.MemberRequestDTO;
import com.homubee.jwebcrawler.dto.response.MemberResponseDTO;
import com.homubee.jwebcrawler.repository.MemberRepository;
import com.homubee.jwebcrawler.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RoleRepository roleRepository;

    public void saveMember(MemberRequestDTO requestDTO) {
        // Save
        Member member = modelMapper.map(requestDTO, Member.class);
        memberRepository.save(member);
        // Give default role (ROLE_USER)
        Role role = Role.builder()
                .member(member)
                .roleType(RoleType.ROLE_USER)
                .build();
        roleRepository.save(role);
    }

    public MemberResponseDTO findById(Long memberId) {
        // find if exists
        Optional<Member> optionalPost = memberRepository.findById(memberId);
        if (optionalPost.isPresent()) {
            Member member = memberRepository.findById(memberId).get();
            MemberResponseDTO responseDTO = modelMapper.map(member, MemberResponseDTO.class);
            return responseDTO;
        } else {
            return new MemberResponseDTO();
        }
    }

    public List<MemberResponseDTO> findMembers() {
        List<Member> members = memberRepository.findAll();
        return modelMapper.map(members, new TypeToken<List<MemberResponseDTO>>(){}.getType());
    }
}
