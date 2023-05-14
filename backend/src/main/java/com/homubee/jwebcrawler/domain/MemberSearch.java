package com.homubee.jwebcrawler.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberSearch {
    private String email;
    private String nickname;
    private Gender gender;
}
