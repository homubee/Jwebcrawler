package com.homubee.jwebcrawler.service;

import com.homubee.jwebcrawler.domain.Member;
import com.homubee.jwebcrawler.domain.MemberSearch;
import com.homubee.jwebcrawler.domain.Role;
import com.homubee.jwebcrawler.domain.RoleType;
import com.homubee.jwebcrawler.dto.request.MemberRegisterRequestDTO;
import com.homubee.jwebcrawler.dto.response.MemberResponseDTO;
import com.homubee.jwebcrawler.repository.MemberRepository;
import com.homubee.jwebcrawler.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
public class MemberService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RoleRepository roleRepository;

    public void saveMember(MemberRegisterRequestDTO requestDTO) {
        Member member = modelMapper.map(requestDTO, Member.class);
        // encode password
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        // check email and save
        validateEmail(member);
        validateDuplicateMember(member);
        boolean isFirst = memberRepository.findAll().isEmpty();
        memberRepository.save(member);

        // give default role (ROLE_USER)
        Role role = Role.builder()
                .member(member)
                .roleType(RoleType.ROLE_USER)
                .build();
        roleRepository.save(role);

        // give admin role for first user
        if (isFirst) {
            Role admin = Role.builder()
                    .member(member)
                    .roleType(RoleType.ROLE_ADMIN)
                    .build();
            roleRepository.save(admin);
        }
    }

    private void validateEmail(Member member) {
        String emailRegex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(member.getEmail()).matches()) {
            throw new IllegalStateException("Wrong email format");
        }
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("Member email is duplicated.");
        }
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

    public Page<MemberResponseDTO> findMembers(MemberSearch memberSearch, Pageable pageable) {
        Page<Member> members = memberRepository.search(memberSearch, pageable);
        return members.map(member -> modelMapper.map(member, MemberResponseDTO.class));
    }
}
