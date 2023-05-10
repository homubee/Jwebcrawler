package com.homubee.jwebcrawler.controller;

import com.homubee.jwebcrawler.dto.request.MemberRequestDTO;
import com.homubee.jwebcrawler.dto.response.MemberResponseDTO;
import com.homubee.jwebcrawler.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/register")
    public void register(@RequestBody MemberRequestDTO requestDTO) {
        memberService.saveMember(requestDTO);
    }
}
