package com.homubee.jwebcrawler.controller;

import com.homubee.jwebcrawler.domain.Gender;
import com.homubee.jwebcrawler.domain.MemberSearch;
import com.homubee.jwebcrawler.dto.request.MemberRegisterRequestDTO;
import com.homubee.jwebcrawler.dto.response.MemberResponseDTO;
import com.homubee.jwebcrawler.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@Tag(name = "[App & Admin] Member", description = "회원 API")
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/{memberId}")
    @Operation(summary = "회원 단건 조회 API", description = "회원 정보(단건)를 조회하는 API입니다.\n\n" +
            "memberId에 해당하는 회원의 정보를 전달합니다.")
    public MemberResponseDTO getSingleMember(@PathVariable("memberId") Long memberId) {
        return memberService.findById(memberId);
    }

    @GetMapping("")
    @Operation(summary = "회원 다수건 조회 API", description = "회원 정보(다수건)를 조회하는 API입니다.\n\n" +
            "email / nickname / gender 기준으로 검색이 가능하며, 페이징 기능을 제공합니다.\n\n" +
            "(단, 페이지는 1부터 시작)")
    public Page<MemberResponseDTO> getMultipleMember(@RequestParam(value = "email", required = false) String email,
                                                     @RequestParam(value = "nickname", required = false) String nickname,
                                                     @RequestParam(value = "gender", required = false) Gender gender,
                                                     @PageableDefault Pageable pageable) {
        return memberService.findMembers(
                MemberSearch.builder()
                        .email(email)
                        .nickname(nickname)
                        .gender(gender)
                        .build(), pageable
        );
    }

    @PostMapping("")
    @Operation(summary = "회원가입 API", description = "회원가입 요청을 수행하는 API입니다.\n\n" +
            "email은 유효성, 중복 검사가 수행됩니다.\n\n" +
            "첫 가입자의 경우, ADMIN 권한이 부여됩니다.")
    public void register(@RequestBody MemberRegisterRequestDTO requestDTO) {
        memberService.saveMember(requestDTO);
    }
}
