package com.homubee.jwebcrawler.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginRequestDTO {
    private String email;
    private String password;
}
