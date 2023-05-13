package com.homubee.jwebcrawler.dto.request;

import com.homubee.jwebcrawler.domain.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDTO {
    private String email;
    private String password;
    private String nickname;
    private Gender gender;
    private String purpose;
}
