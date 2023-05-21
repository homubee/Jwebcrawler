package com.homubee.jwebcrawler.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginResponseDTO {
    private Long memberId;
    private String accessToken;
}
