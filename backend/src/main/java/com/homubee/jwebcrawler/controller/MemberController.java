package com.homubee.jwebcrawler.controller;

import com.homubee.jwebcrawler.domain.Gender;
import com.homubee.jwebcrawler.domain.MemberSearch;
import com.homubee.jwebcrawler.dto.request.MemberRegisterRequestDTO;
import com.homubee.jwebcrawler.dto.response.MemberResponseDTO;
import com.homubee.jwebcrawler.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@Tag(name = "[App] Member", description = "회원 API")
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/{memberId}")
    public MemberResponseDTO getSingleMember(@PathVariable("memberId") Long memberId) {
        return memberService.findById(memberId);
    }

    @GetMapping("")
    public List<MemberResponseDTO> getMultipleMember(@RequestParam(value = "email", required = false) String email,
                                                     @RequestParam(value = "nickname", required = false) String nickname,
                                                     @RequestParam(value = "gender", required = false) Gender gender) {
        return memberService.findMembers(
                MemberSearch.builder()
                .email(email)
                .nickname(nickname)
                .gender(gender)
                .build());
    }

    @PostMapping("")
    public void register(@RequestBody MemberRegisterRequestDTO requestDTO) {
        memberService.saveMember(requestDTO);
    }
}
